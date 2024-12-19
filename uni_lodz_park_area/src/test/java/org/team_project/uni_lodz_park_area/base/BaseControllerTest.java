package org.team_project.uni_lodz_park_area.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.team_project.uni_lodz_park_area.builder.UserEntityBuilder;
import org.team_project.uni_lodz_park_area.logging.entity.LogEntity;
import org.team_project.uni_lodz_park_area.logging.service.impl.LogServiceImpl;
import org.team_project.uni_lodz_park_area.security.CustomUserDetails;
import org.team_project.uni_lodz_park_area.security.CustomUserDetailsService;
import org.team_project.uni_lodz_park_area.security.jwt.JwtUtils;
import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseControllerTest extends AbstractTestContainerConfiguration {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected CustomUserDetailsService customUserDetailsService;

    @MockBean
    protected LogServiceImpl logService;

    @Autowired
    protected JwtUtils jwtUtils;

    protected UserEntity mockUserEntity;

    protected String mockUserToken;

    protected UserEntity mockAdmin;

    protected String mockAdminToken;


    @BeforeEach
    protected void initializeAuth() {

        this.mockUserEntity = new UserEntityBuilder().customer().build();
        this.mockAdmin = new UserEntityBuilder().admin().build();

        final CustomUserDetails mockUserDetails = new CustomUserDetails(mockUserEntity);
        final CustomUserDetails mockAdminDetails = new CustomUserDetails(mockAdmin);

        this.mockUserToken = generateMockToken(mockUserDetails);
        this.mockAdminToken = generateMockToken(mockAdminDetails);

        Mockito.when(customUserDetailsService.loadUserByUsername(mockUserEntity.getEmail())).thenReturn(mockUserDetails);
        Mockito.when(customUserDetailsService.loadUserByUsername(mockAdmin.getEmail())).thenReturn(mockAdminDetails);
        Mockito.doNothing().when(logService).saveLogToDatabase(Mockito.any(LogEntity.class));

    }

    private String generateMockToken(CustomUserDetails details) {
        return "Bearer ".concat(jwtUtils.generateJwtToken(details));
    }
}
