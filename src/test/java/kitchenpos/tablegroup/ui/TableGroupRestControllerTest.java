package kitchenpos.tablegroup.ui;

import static kitchenpos.util.TestDataSet.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kitchenpos.tablegroup.application.TableGroupService;
import kitchenpos.tablegroup.dto.TableGroupResponse;

@WebMvcTest(controllers = TableGroupRestController.class)
@ExtendWith(MockitoExtension.class)
public class TableGroupRestControllerTest {

    public static final String BASE_URL = "/api/table-groups";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TableGroupService tableGroupService;

    @Test
    @DisplayName("개별 테이블들을 단체손님 테이블 그룹으로 묶을 수 있따.")
    void create() throws Exception {
        // given
        String content = objectMapper.writeValueAsString(산악회);
        given(tableGroupService.create(any())).willReturn(TableGroupResponse.of(산악회));

        // when
        mockMvc.perform(
            post(BASE_URL)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(산악회.getId()))
            .andExpect(jsonPath("$.orderTables[0].id").value(테이블_1번.getId()))
            .andExpect(jsonPath("$.orderTables[1].id").value(테이블_2번.getId()));
    }

    @Test
    @DisplayName("단체손님 그룹에서 개별 테이블을 제거할 수 있다.")
    void ungroup() throws Exception {
        // when
        mockMvc.perform(delete(BASE_URL + "/{tableGroupId}", 산악회.getId()))
            .andExpect(status().isNoContent());
    }

}