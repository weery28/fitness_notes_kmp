package me.coweery.fitnessnotes

import com.github.florent37.preferences.Preferences
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import me.coweery.fitnessnotes.presenters.login.LoginScreenContract
import me.coweery.fitnessnotes.presenters.login.LoginScreenPresenter
import me.coweery.fitnessnotes.presenters.splash.SplashScreenContract
import me.coweery.fitnessnotes.presenters.splash.SplashScreenPresenter
import me.coweery.fitnessnotes.repository.login.KtorLoginRepository
import me.coweery.fitnessnotes.repository.login.LoginRepository
import me.coweery.fitnessnotes.repository.token.PreferencesTokenRepository
import me.coweery.fitnessnotes.repository.token.TokenRepository
import me.coweery.fitnessnotes.services.login.LoginService
import me.coweery.fitnessnotes.services.login.LoginServiceImpl
import me.coweery.fitnessnotes.services.token.TokenService
import me.coweery.fitnessnotes.services.token.TokenServiceImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

object DI {

    val kodein = DI {

        bind<SplashScreenContract.Presenter>() with provider { SplashScreenPresenter(instance()) }
        bind<TokenRepository>() with singleton { PreferencesTokenRepository(instance()) }
        bind<TokenService>() with singleton { TokenServiceImpl(instance()) }
        bind<Preferences>() with singleton { Preferences() }

        bind<HttpClient>() with singleton {
            HttpClient() {
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            println(message)
                        }
                    }
                    level = LogLevel.ALL
                }
            }
        }

        bind<LoginRepository>() with singleton { KtorLoginRepository(instance()) }
        bind<LoginService>() with singleton { LoginServiceImpl(instance()) }

        bind<LoginScreenContract.Presenter>() with provider {
            LoginScreenPresenter(
                instance(),
                instance()
            )
        }
    }
}