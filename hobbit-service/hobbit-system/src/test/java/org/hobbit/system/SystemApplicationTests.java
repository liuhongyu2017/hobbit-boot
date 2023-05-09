package org.hobbit.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hobbit.core.test.HobbitBootTest;
import org.hobbit.system.service.IDictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@HobbitBootTest(enableLoader = true)
@SpringBootTest(classes = SystemApplication.class)
class SystemApplicationTests {

  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  IDictService dictService;

  @Test
  void contextLoads() throws JsonProcessingException {
    System.out.println(objectMapper.writeValueAsString(dictService.tree()));
  }

}
