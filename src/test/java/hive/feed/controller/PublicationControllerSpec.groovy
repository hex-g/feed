package hive.feed.controller


import hive.feed.repository.PublicationRepository
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import static hive.pandora.constant.HiveInternalHeaders.AUTHENTICATED_USER_ID
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class PublicationControllerSpec extends Specification {

    PublicationRepository publicationRepository
    RestTemplate restTemplate

    MockMvc mockMvc

    def urlBase = 'http://localhost:9700/publication'

    def setup() {
        publicationRepository = Stub()
        restTemplate = Stub()

        final def publicationController = new PublicationController(publicationRepository)

        mockMvc = MockMvcBuilders.standaloneSetup(publicationController).build()
    }

    def "Should return NOT ACCEPTABLE when POST only with null values"() {

        given:
        final def userId = "1"

        expect:
        mockMvc.perform(
                post("${urlBase}")
                .header(AUTHENTICATED_USER_ID, userId)
        ).andExpect(status().isNotAcceptable())
                .andExpect(status().reason("Null value not allowed"))

    }

    def "Should return NOT ACCEPTABLE when MESSAGE Provided with white spaces"() {

        given:
        final def userId = "1"
        final def type = "STATUS"
        final def message = "     "

        expect:
        mockMvc.perform(
                post("${urlBase}")
                        .header(AUTHENTICATED_USER_ID, userId)
                        .param("type", type)
                        .param("message", message)
        ).andExpect(status().isNotAcceptable())
                .andExpect(status().reason("Null value not allowed"))

    }

    def "Should return NOT FOUND when Update Post with publication id nonexistent"() {

        given:
        final def userId = "1"
        final def publicationId = "1"
        final def type = "STATUS"
        final def message = "Test"

        expect:
        mockMvc.perform(
                post("${urlBase}")
                        .header(AUTHENTICATED_USER_ID, userId)
                        .param("publicationId", publicationId)
                        .param("type", type)
                        .param("message", message)
        ).andExpect(status().isNotFound())
                .andExpect(status().reason("Publication not found"))

    }

    def "Should return NOT FOUND when no NOTIFICATIONS in dataBase"() {

        given:
        final def userId = "1"
        final def type = "NOTIFICATIONS"

        expect:
        mockMvc.perform(
                get("${urlBase}")
                        .header(AUTHENTICATED_USER_ID, userId)
                        .param("type", type)
        ).andExpect(status().isNotFound())
                .andExpect(status().reason("Publication not found"))

    }

    def "Should return NOT FOUND when Delete with publication id nonexistent"() {

        given:
        final def userId = "1"
        final def publicationId = "1"

        expect:
        mockMvc.perform(
                delete("${urlBase}")
                        .header(AUTHENTICATED_USER_ID, userId)
                        .param("publicationId", publicationId)
        ).andExpect(status().isNotFound())
                .andExpect(status().reason("Publication not found"))

    }
}
