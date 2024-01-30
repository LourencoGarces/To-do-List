import java.io.*;
import java.util.logging.*;
import java.util.ArrayList;
public class Persistencia_Dados {
    private static final String NOME_ARQUIVO = "tarefas.txt";
    private static final Logger logger = Logger.getLogger(Persistencia_Dados.class.getName());
    public static void salvarTarefas(ArrayList<Tarefas> tarefas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Tarefas t : tarefas) {
                writer.println(t.getTitulo() + ";" + t.getDescricao() + ";" + t.getEstado());
            }
            logger.info("Tarefas salvas em " + NOME_ARQUIVO);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao salvar tarefas em " + NOME_ARQUIVO, e);
        }
    }
    public static ArrayList<Tarefas> carregarTarefas() {
        ArrayList<Tarefas> tarefas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    Tarefas tarefa = new Tarefas(partes[0], partes[1], partes[2]);
                    tarefas.add(tarefa);
                }
            }
            logger.info("Tarefas carregadas de " + NOME_ARQUIVO);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao carregar tarefas de " + NOME_ARQUIVO, e);
        }
        return tarefas;
    }
}