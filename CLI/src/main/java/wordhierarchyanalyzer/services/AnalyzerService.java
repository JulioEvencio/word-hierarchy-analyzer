package wordhierarchyanalyzer.services;

import org.json.JSONArray;
import org.json.JSONObject;
import wordhierarchyanalyzer.collections.tree.Tree;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AnalyzerService {

    private final Tree tree;

    public AnalyzerService(String file) {
        tree = new Tree();

        try {
            String content = new String(Files.readAllBytes(Paths.get(file)));
            JSONObject jsonObject = new JSONObject(content);

            this.processJson(jsonObject, "");
        } catch (Exception e) {
            System.out.println("Erro ao carregar arquivo...");
        }
    }

    private void processJson(JSONObject jsonObject, String parent) {
        jsonObject.keys().forEachRemaining(key -> {
            Object value = jsonObject.get(key);

            if (value instanceof JSONObject) {
                tree.addText(key, parent, false);
                this.processJson((JSONObject) value, key);
            } else if (value instanceof JSONArray) {
                tree.addText(key, parent, false);

                for (int i = 0; i < ((JSONArray) value).length(); i++) {
                    tree.addText(((JSONArray) value).getString(i), key, true);
                }
            }
        });
    }

    private String depthAux(String phrase, int depth) {
        Map<String, Integer> match = new HashMap<>();
        StringBuilder out = new StringBuilder();

        for (String text : phrase.split(" ")) {
            Optional<String> parent = tree.depthLogic(text, depth);

            if (parent.isPresent()) {
                int value = (match.containsKey(parent.get())) ? match.get(parent.get()) + 1 : 1;

                match.put(parent.get(), value);
            }
        }

        if (match.isEmpty()) {
            out = new StringBuilder(String.format("Na frase não existe nenhum filho do nível %d e nem o nível %d possui os termos especificados", depth, depth));
        } else {
            for (String key : match.keySet()) {
                out.append(key).append(" = ").append(match.get(key)).append("; ");
            }
        }

        return out.toString();
    }

    public void depth(String[] args) {
        long startParam = System.currentTimeMillis();

        int depth = Integer.parseInt(args[2]);
        boolean isVerbose = args.length == 5;
        String phrase = (isVerbose) ? (args[3].equals("--verbose") ? args[4] : args[3]) : args[3];

        long endParam = System.currentTimeMillis();

        long startPhrase = System.currentTimeMillis();

        String res = this.depthAux(phrase, depth);

        long endPhrase = System.currentTimeMillis();

        if (isVerbose) {
            System.out.println("--------------------------------------");
            System.out.println("Tempo de carregamento dos parâmetros: " + (endParam - startParam) + "ms");
            System.out.println("--------------------------------------");

            System.out.println("--------------------------------------");
            System.out.println("Tempo de verificação da frase: " + (endPhrase - startPhrase) + "ms");
            System.out.println("--------------------------------------");
        }

        System.out.println(res);
    }

}
