import service.LLMService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        LLMService llm = new LLMService();

        String code = """
                public class Test {
                    public static int main(String[] args) {
                        System.out.println("Hello World")
                    }
                }
                """;

        String prompt = """
            Analysiere den folgenden Java-Code.

            1. Finde ALLE Syntaxfehler
            2. Erkläre die Fehler
            3. Gib eine korrigierte Version zurück

            Antworte klar und präzise.

            Code:
            """ + code;

        String feedback = llm.generate(prompt);

        System.out.println("\n==== AI FEEDBACK ====");
        System.out.println(feedback);
    }
}