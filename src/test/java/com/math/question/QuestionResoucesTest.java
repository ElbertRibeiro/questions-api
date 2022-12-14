package com.math.question;

import com.math.matter.MatterDTO;
import com.math.subject.SubjectDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) class QuestionResoucesTest {
    @Autowired private TestRestTemplate testRestTemplate;
    @Autowired private QuestionService service;

    @Test void getQuestionDbTest() {
        QuestionDTO dtos = new QuestionDTO();
        SubjectDTO subjectDTO = new SubjectDTO();
        MatterDTO matterDTO = new MatterDTO();
        matterDTO.setTitle("teste");
        subjectDTO.setSubjectName("teste");
        dtos.setDescription("Testee");
        dtos.setMatter(matterDTO);
        dtos.setNivel(2);
        dtos.setRightAnswer("10");
        dtos.setSubject(subjectDTO);
        HttpEntity<QuestionDTO> httpEntity = new HttpEntity<>(dtos);
        ResponseEntity<String> response =
                testRestTemplate.exchange("/question/create", HttpMethod.POST, httpEntity, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        service.deleteQuestion(Long.valueOf(Objects.requireNonNull(response.getBody())));
    }
}
