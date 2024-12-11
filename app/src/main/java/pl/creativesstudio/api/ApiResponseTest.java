package pl.creativesstudio.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    private ApiResponse apiResponse;
    private List<Bus> busList;



    @Test
    void testGetResultWhenListIsNull() {
        assertNull(apiResponse.getResult(), "Poczatkowa wartosc pola result powinna byc null");
    }

    @Test
    void testSetResult() {
        apiResponse.setResult(busList);
        assertNotNull(apiResponse.getResult(), "Wynik nie powinien byc null po ustawieniu listy");
        assertEquals(2, apiResponse.getResult().size(), "Lista powinna miec 2 elementy");
    }

    @Test
    void testGetResultReturnsCorrectList() {
        apiResponse.setResult(busList);
        List<Bus> result = apiResponse.getResult();
        
        assertNotNull(result, "Wynik nie powinien byc null");
        assertEquals(busList, result, "Lista powinna byc identyczna z ustawiona lista");
    }

    @Test
    void testSetResultOverwritesPreviousValue() {
        List<Bus> newBusList = new ArrayList<>();
        newBusList.add(new Bus("Bus3", "Route3", "12:00"));
        
        apiResponse.setResult(busList);
        apiResponse.setResult(newBusList);
        
        assertEquals(1, apiResponse.getResult().size(), "Lista powinna zawierac jeden element po nadpisaniu");
        assertEquals("Bus3", apiResponse.getResult().get(0).getName(), "Nazwa autobusu powinna byc Bus3");
    }
} 



import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class WarsawApiServiceTest {

    private WarsawApiService warsawApiService;
    private Call<ApiResponse> mockCall;

    @BeforeEach
    void setUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.um.warszawa.pl/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        warsawApiService = retrofit.create(WarsawApiService.class);
        mockCall = mock(Call.class);
    }

    @Test
    void testGetBusesWithRequiredParameters() {
        WarsawApiService service = mock(WarsawApiService.class);
        String resourceId = "exampleResourceId";
        String apiKey = "exampleApiKey";
        int type = 1;

        when(service.getBuses(resourceId, apiKey, type, null, null)).thenReturn(mockCall);
        Call<ApiResponse> call = service.getBuses(resourceId, apiKey, type, null, null);

        assertNotNull(call, "Wywołanie uslugi powinno zwrocic Call<ApiResponse>");
        verify(service).getBuses(resourceId, apiKey, type, null, null);
    }

    @Test
    void testGetBusesWithAllParameters() {
        WarsawApiService service = mock(WarsawApiService.class);
        String resourceId = "exampleResourceId";
        String apiKey = "exampleApiKey";
        int type = 1;
        String line = "123";
        String brigade = "A1";

        when(service.getBuses(resourceId, apiKey, type, line, brigade)).thenReturn(mockCall);
        Call<ApiResponse> call = service.getBuses(resourceId, apiKey, type, line, brigade);

        assertNotNull(call, "Wywołanie uslugi powinno zwrocic Call<ApiResponse>");
        verify(service).getBuses(resourceId, apiKey, type, line, brigade);
    }
} 
