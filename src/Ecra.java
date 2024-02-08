import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.time.LocalTime;
public class Ecra extends JFrame{
    private ArrayList<Tarefas> tarefas = new ArrayList<>();
    private Timer timer;
    public Ecra() {
        /*private static void menu() {
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Editar tarefa");
            System.out.println("4 - Remover tarefa");
            System.out.println("5 - Mudar estado da tarefa");
            System.out.println("6 - Salvar tarefas");
            System.out.println("7 - Carregar tarefas");
            System.out.println("0 - Sair");
        }*/

        //ecrã
        setTitle("Tarefas");
        setVisible(true);
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel To_Do_List = new JLabel("To do List");
        To_Do_List.setBounds(375, 1, 250, 70);
        To_Do_List.setFont(new Font("Arial", Font.ITALIC, 30));
        To_Do_List.setForeground(Color.BLACK);
        add(To_Do_List);

        //botão
        setLayout(null);
        JButton botao = new JButton("Adicionar tarefa");
        botao.setBounds(30, 100, 200, 50);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setForeground(Color.BLACK);
        botao.setBackground(Color.GRAY);
        add(botao);

        JButton botao2 = new JButton("Listar tarefas");
        botao2.setBounds(260, 100, 200, 50);
        botao2.setFont(new Font("Arial", Font.BOLD, 18));
        botao2.setForeground(Color.BLACK);
        botao2.setBackground(Color.GRAY);
        add(botao2);

        JButton botao3 = new JButton("Editar tarefa");
        botao3.setBounds(490, 100, 200, 50);
        botao3.setFont(new Font("Arial", Font.BOLD, 18));
        botao3.setForeground(Color.BLACK);
        botao3.setBackground(Color.GRAY);
        add(botao3);

        JButton botao4 = new JButton("Remover tarefa");
        botao4.setBounds(720, 100, 200, 50);
        botao4.setFont(new Font("Arial", Font.BOLD, 18));
        botao4.setForeground(Color.BLACK);
        botao4.setBackground(Color.GRAY);
        add(botao4);

        JButton botao5 = new JButton("Mudar estado da tarefa");
        botao5.setBounds(130, 200, 200, 50);
        botao5.setFont(new Font("Arial", Font.BOLD, 15));
        botao5.setForeground(Color.BLACK);
        botao5.setBackground(Color.GRAY);
        add(botao5);

        JButton botao6 = new JButton("Salvar tarefas");
        botao6.setBounds(380, 200, 200, 50);
        botao6.setFont(new Font("Arial", Font.BOLD, 18));
        botao6.setForeground(Color.BLACK);
        botao6.setBackground(Color.GRAY);
        add(botao6);

        JButton botao7 = new JButton("Carregar tarefas");
        botao7.setBounds(635, 200, 200, 50);
        botao7.setFont(new Font("Arial", Font.BOLD, 18));
        botao7.setForeground(Color.BLACK);
        botao7.setBackground(Color.GRAY);
        add(botao7);

        JButton botao8 = new JButton("Defenir Lembrete");
        botao8.setBounds(380, 300, 200, 50);
        botao8.setFont(new Font("Arial", Font.BOLD, 18));
        botao8.setForeground(Color.BLACK);
        botao8.setBackground(Color.GRAY);
        add(botao8);

        JButton botao9 = new JButton("Sair");
        botao9.setBounds(750, 900, 200, 50);
        botao9.setFont(new Font("Arial", Font.BOLD, 18));
        botao9.setForeground(Color.BLACK);
        botao9.setBackground(Color.GRAY);
        add(botao9);

        //Acções
        botao.addActionListener(this::acao1);
        botao2.addActionListener(this::acao2);
        botao3.addActionListener(this::acao3);
        botao4.addActionListener(this::acao4);
        botao5.addActionListener(this::acao5);
        botao6.addActionListener(this::acao6);
        botao7.addActionListener(this::acao7);
        botao8.addActionListener(this::acao8);
        botao9.addActionListener(this::acao9);
    }
    private void acao9(ActionEvent actionEvent) {
        System.exit(0);
    }
    private void acao8(ActionEvent actionEvent) {
        String horaStr = JOptionPane.showInputDialog("Digite a hora do lembrete (HH:mm):");

        if (horaStr == null || horaStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hora do lembrete não fornecida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Parse da hora fornecida
            LocalTime horaLembrete = LocalTime.parse(horaStr);

            // Calcular o tempo até a hora do lembrete em milissegundos
            long delay = horaLembrete.toNanoOfDay() - LocalTime.now().toNanoOfDay();

            if (delay <= 0) {
                JOptionPane.showMessageDialog(null, "A hora do lembrete deve ser no futuro.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Configurar o Timer para exibir uma notificação na hora do lembrete
            timer = new Timer((int) (delay / 1_000_000), evt -> {
                exibirNotificacao("Lembrete: Hora de fazer algo!");
                timer.stop(); // Parar o Timer após a notificação
            });
            timer.setRepeats(false); // Não repetir o Timer
            timer.start(); // Iniciar o Timer

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Formato de hora inválido. Use HH:mm.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void exibirNotificacao(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Lembrete", JOptionPane.INFORMATION_MESSAGE);
    }
    private void acao7(ActionEvent actionEvent) {
        tarefas = Persistencia_Dados.carregarTarefas();
    }
    private void acao6(ActionEvent actionEvent) {
        Persistencia_Dados.salvarTarefas(tarefas);
    }
    private void acao5(ActionEvent actionEvent) {
        String titulo = JOptionPane.showInputDialog("Digite o título da tarefa:");
        String estado = JOptionPane.showInputDialog("Digite o novo estado da tarefa:");
        for (Tarefas t : tarefas) {
            if (t.getTitulo().equals(titulo)) {
                t.setEstado(estado);
                JOptionPane.showMessageDialog(null, "Estado da tarefa alterado com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Tarefa não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    private void acao4(ActionEvent actionEvent) {
        String titulo = JOptionPane.showInputDialog("Digite o título da tarefa:");
        for (Tarefas t : tarefas) {
            if (t.getTitulo().equals(titulo)) {
                tarefas.remove(t);
                JOptionPane.showMessageDialog(null, "Tarefa removida com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Tarefa não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    private void acao3(ActionEvent actionEvent) {
        String titulo = JOptionPane.showInputDialog("Digite o título da tarefa que deseja editar:");

        for (Tarefas t : tarefas) {
            if (t.getTitulo().equals(titulo)) {
                escolherEditarTarefa(t);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Tarefa não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    private void escolherEditarTarefa(Tarefas t) {
        String[] opcoes = {"Editar título", "Editar descrição", "Sair"};
        int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Editar Tarefa", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        switch (escolha) {
            case 0:
                // Editar título
                String novoTitulo = JOptionPane.showInputDialog("Digite o novo título da tarefa:");
                t.setTitulo(novoTitulo);
                break;
            case 1:
                // Editar descrição
                String novaDescricao = JOptionPane.showInputDialog("Digite a nova descrição da tarefa:");
                t.setDescricao(novaDescricao);
                break;
            case 2:
                // Sair
                JOptionPane.showMessageDialog(null, "Saindo...", "Editar Tarefa", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                // Opção inválida
                JOptionPane.showMessageDialog(null, "Opção inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
    private void acao2(ActionEvent actionEvent) {
        StringBuilder lista = new StringBuilder();
        if (tarefas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tarefa não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            for (Tarefas t : tarefas) {
                lista.append(t).append("\n");
            }
            JOptionPane.showMessageDialog(null, "Lista de Tarefas:\n\n" + lista, "Listar Tarefas", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void acao1(ActionEvent actionEvent) {
        String titulo = JOptionPane.showInputDialog("Digite o título da tarefa:");
        if (titulo == null || titulo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Título inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String descricao = JOptionPane.showInputDialog("Digite a descrição da tarefa:");
        String estado = JOptionPane.showInputDialog("Digite o estado da tarefa:");
        Tarefas tarefa = new Tarefas(titulo, descricao, estado);
        tarefas.add(tarefa);
        JOptionPane.showMessageDialog(null, "Tarefa adicionada com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }
}