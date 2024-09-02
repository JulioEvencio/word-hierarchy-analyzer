package wordhierarchyanalyzer;

import wordhierarchyanalyzer.services.AnalyzerService;

public class Main {

    public static void main(String[] args) {
        AnalyzerService analyzerService = new AnalyzerService("dicts/data.json");
        System.out.println(analyzerService.depth(args));
    }

}
