package com;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.any;
import org.mockito.MockitoAnnotations;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by shenzhiqiang on 16-10-12.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DESedeUtils.class, PointsApiClient.class}) // static
public class UTest {
    @Mock
    CardCustomerNoMapper cardCustomerNoMapper; // 注入mock
    @Mock
    POSSearchRecordMapper posSearchRecordMapper;

    @InjectMocks
    SearchServiceImpl searchService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearch() throws Exception {

        SearchReq searchReq = new SearchReq();

        mockStatic(DESedeUtils.class);
        when(DESedeUtils.decode(any(String.class), any(String.class))).thenReturn(null);
        SearchResp searchResp = searchService.search(searchReq);
        assertEquals(searchResp.getMsg_rsp_code(), RespCodeDescParam.CODE_PARAM_ERR);


        when(cardCustomerNoMapper.getCardCustomerNoInfoByCardNo(any(String.class))).thenReturn(null);

        searchService.setRetryTimes("1"); // @Value 设置
    }
}
