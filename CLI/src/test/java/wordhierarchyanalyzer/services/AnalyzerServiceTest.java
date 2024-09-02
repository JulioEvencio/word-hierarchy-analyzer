package wordhierarchyanalyzer.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalyzerServiceTest {

    private static AnalyzerService analyzerService;

    @BeforeAll
    public static void setup() {
        AnalyzerServiceTest.analyzerService = new AnalyzerService("dicts/data.json");
    }

    private String[] createParams(String depth, String phrase) {
        return new String[] {
                "analyze",
                "--depth",
                depth,
                phrase
        };
    }

    @Test
    public void example01() {
        String[] params = this.createParams("2", "Eu amo papagaios");

        String actual = AnalyzerServiceTest.analyzerService.depth(params);
        String expected = "Aves = 1; ";

        assertEquals(expected, actual);
    }

    @Test
    public void example02() {
        String[] params = this.createParams("3", "Eu vi gorilas e papagaios");

        String actual = AnalyzerServiceTest.analyzerService.depth(params);
        String expected = "Pássaros = 1; Primatas = 1; ";

        assertEquals(expected, actual);
    }

    @Test
    public void example03() {
        String[] params = this.createParams("5", "Eu tenho preferência por animais carnívoros");

        String actual = AnalyzerServiceTest.analyzerService.depth(params);
        String expected = "Na frase não existe nenhum filho do nível 5 e nem o nível 5 possui os termos especificados";

        assertEquals(expected, actual);
    }

    @Test
    public void exampleWith5000Characters() {
        String phrase = " ABC ".repeat(5000) +
                " Leões " +
                " Leopardos" +
                " Cavalos " +
                " Bois " +
                " Orangotangos " +
                " Canários " +
                " Corujas ";

        String[] params = new String[] {
                "analyze",
                "--depth",
                "3",
                phrase
        };

        String actual = AnalyzerServiceTest.analyzerService.depth(params);
        String expected = "Pássaros = 1; Herbívoros = 1; Carnívoros = 2; Primatas = 1; Rapinas = 1; Bovídeos = 1; ";

        assertEquals(expected, actual);
    }

}
