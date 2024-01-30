import java.util.*;
public class APP {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Tarefas> tarefas = new ArrayList<>();
    public static void main(String[] args) {
        int opcao;
        do {
            menu();
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1:
                    adicionarTarefa();
                    break;
                case 2:
                    listarTarefas();
                    break;
                case 3:
                    editarTarefa();
                    break;
                case 4:
                    removerTarefa();
                    break;
                case 5:
                    mudarEstado();
                    break;
                case 6:
                    Persistencia_Dados.salvarTarefas(tarefas);
                    break;
                case 7:
                    tarefas = Persistencia_Dados.carregarTarefas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }
    private static void menu() {
        System.out.println("1 - Adicionar tarefa");
        System.out.println("2 - Listar tarefas");
        System.out.println("3 - Editar tarefa");
        System.out.println("4 - Remover tarefa");
        System.out.println("5 - Mudar estado da tarefa");
        System.out.println("6 - Salvar tarefas");
        System.out.println("7 - Carregar tarefas");
        System.out.println("0 - Sair");
    }
    private static void adicionarTarefa() {
        System.out.println("Digite o título da tarefa:");
        String titulo = input.nextLine();
        System.out.println("Digite a descrição da tarefa:");
        String descricao = input.nextLine();
        System.out.println("Digite o estado da tarefa:");
        String estado = input.nextLine();
        Tarefas tarefa = new Tarefas(titulo, descricao, estado);
        tarefas.add(tarefa);
    }
    private static void listarTarefas() {
        for (Tarefas t : tarefas) {
            System.out.println(t);
        }
    }
    private static void editarTarefa() {
        System.out.println("Digite o título da tarefa que deseja editar:");
        String titulo = input.nextLine();
        for (Tarefas t : tarefas) {
            if (t.getTitulo().equals(titulo)) {
                escolherEditarTarefa(t);
            }
        }
    }
    private static void escolherEditarTarefa(Tarefas t) {
        System.out.println("1 - Editar título");
        System.out.println("2 - Editar descrição");
        System.out.println("0 - Sair");
        int opcao = input.nextInt();
        input.nextLine();
        switch (opcao) {
            case 1:
                System.out.println("Digite o novo título da tarefa:");
                String novoTitulo = input.nextLine();
                t.setTitulo(novoTitulo);
                break;
            case 2:
                System.out.println("Digite a nova descrição da tarefa:");
                String novaDescricao = input.nextLine();
                t.setDescricao(novaDescricao);
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
    private static void removerTarefa() {
        System.out.println("Digite o título da tarefa que deseja remover:");
        String titulo = input.nextLine();
        tarefas.removeIf(t -> t.getTitulo().equals(titulo));
    }
    private static void mudarEstado() {
        System.out.println("Digite o título da tarefa que deseja mudar o estado:");
        String titulo = input.nextLine();
        for (Tarefas t : tarefas) {
            if (t.getTitulo().equals(titulo)) {
                escolherMudarEstado(t);
            }
        }
    }
    private static void escolherMudarEstado(Tarefas t) {
        System.out.println("1 - Fazer");
        System.out.println("2 - Fazendo");
        System.out.println("3 - Feito");
        System.out.println("0 - Sair");
        int opcao = input.nextInt();
        input.nextLine();
        switch (opcao) {
            case 1:
                t.setEstado("Fazer");
                break;
            case 2:
                t.setEstado("Fazendo");
                break;
            case 3:
                t.setEstado("Feito");
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}
