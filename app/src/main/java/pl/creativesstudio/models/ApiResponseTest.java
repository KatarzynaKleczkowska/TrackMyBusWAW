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

