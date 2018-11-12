package com.invasionofsmallcubes.traveldodolist

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.templateresolver.ServletContextTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import javax.servlet.ServletContext
import org.springframework.context.support.ResourceBundleMessageSource



@Configuration
class Configuration {

    @Bean
    fun tripRepository(): TripRepository {
        return InMemoryTripRepository()
    }

    @Bean
    fun templateResolver(servletContext: ServletContext): ServletContextTemplateResolver {
        val templateResolver = ServletContextTemplateResolver(servletContext)
        templateResolver.prefix = "/build/"
        templateResolver.suffix = ".html"
        templateResolver.setTemplateMode("HTML5")
        return templateResolver
    }

    @Bean
    fun templateEngine(servletContext: ServletContext): SpringTemplateEngine {
        val templateEngine = SpringTemplateEngine()
        templateEngine.setTemplateResolver(templateResolver(servletContext))
        templateEngine.setTemplateEngineMessageSource(messageSource());
        return templateEngine
    }

    @Bean
    fun viewResolver(servletContext: ServletContext): ThymeleafViewResolver {
        val viewResolver = ThymeleafViewResolver()
        viewResolver.templateEngine = templateEngine(servletContext)
        viewResolver.order = 1
        return viewResolver
    }

    @Bean
    fun messageSource(): ResourceBundleMessageSource {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasename("messages")
        return messageSource
    }

}