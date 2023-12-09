package com.example.domain.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class JwtService{

    @Value("\${jwt.secret.access}")
    private lateinit var jwtAccessSecret: String

    @Value("\${jwt.secret.refresh}")
    private lateinit var jwtRefreshSecret: String

    private val log = LoggerFactory.getLogger(javaClass)


    fun generateAccessToken(userDetails: UserDetails): String {
        return Jwts
            .builder()
            .setSubject(userDetails.username)
            .setClaims(
                mapOf(
                    "username" to userDetails.username,
                    "password" to userDetails.password
                )
            )
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1L)))
            .signWith(getAccessSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun generateRefreshToken(userDetails: UserDetails): String {
        return Jwts
            .builder()
            .setSubject(userDetails.username)
            .setClaims(
                mapOf(
                    "username" to userDetails.username,
                    "password" to userDetails.password,
                    "roles" to userDetails.authorities.joinToString()
                )
            )
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1L)))
            .signWith(getAccessSignInKey(), SignatureAlgorithm.HS512)
            .compact()
    }

    fun extractUsernameFromAccessToken(jwtToken: String): String {
        return extractClaims(jwtToken, getAccessSignInKey()){
            it["username"] as String
        }
    }

    fun extractUsernameFromRefreshToken(jwtToken: String): String {
        return extractClaims(jwtToken, getRefreshSignInKey()){
            it["username"] as String
        }
    }

    fun isAccessTokenValid(jwtToken: String): Boolean {
        val key = getAccessSignInKey()
        return isValidToken(jwtToken, key) && isNotExpired(jwtToken, key)
    }

    fun isRefreshTokenValid(jwtToken: String): Boolean {
        val key = getRefreshSignInKey()
        return isValidToken(jwtToken, key) && isNotExpired(jwtToken, key)
    }


    private fun <T> extractClaims(jwtToken: String, key: Key, resolver: (Claims) -> T): T{
        val claims = extractAllClaims(jwtToken, key)
        return resolver(claims)
    }

    private fun extractAllClaims(jwtToken: String, key: Key): Claims{
        return Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwtToken)
            .body
    }

    private fun getAccessSignInKey(): Key{
        val bytes = Decoders.BASE64.decode(jwtAccessSecret)
        return Keys.hmacShaKeyFor(bytes)
    }

    private fun isNotExpired(jwtToken: String, key: Key): Boolean{
        val expiration = extractClaims(jwtToken, key){
            it.expiration
        }

        return expiration.before(Date(System.currentTimeMillis()))
    }

    private fun isValidToken(jwtToken: String, key: Key): Boolean{
        try {
            Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
            return true
        } catch (e: ExpiredJwtException) {
            log.error("Token expired", e)
        } catch (e: UnsupportedJwtException) {
            log.error("Unsupported jwt", e)
        } catch (e: MalformedJwtException) {
            log.error("Malformed jwt", e)
        } catch (e: SignatureException) {
            log.error("Invalid signature", e)
        } catch (e: Exception) {
            log.error("Invalid token", e)
        }
        return false
    }


    private fun getRefreshSignInKey(): Key{
        val bytes = Decoders.BASE64.decode(jwtRefreshSecret)
        return Keys.hmacShaKeyFor(bytes)
    }
}