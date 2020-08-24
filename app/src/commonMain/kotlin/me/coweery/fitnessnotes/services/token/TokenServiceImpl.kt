package me.coweery.fitnessnotes.services.token

import me.coweery.fitnessnotes.repository.token.TokenRepository

class TokenServiceImpl(
    private val tokenRepository: TokenRepository
) : TokenService {

    override fun getToken(): String? = tokenRepository.get()

    override fun saveToken(token: String) {
        tokenRepository.save(token)
    }
}