import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.time.LocalTime;
public class ToDoListFrame extends JFrame{
    private ArrayList<Tarefas> tarefas = new ArrayList<>();
    private Timer timer;
    public ToDoListFrame() {
        setTitle("Tarefas");
        setVisible(true);
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel To_Do_List = new JLabel("To do List");
        To_Do_List.setBounds(375, 1, 250, 70);
        To_Do_List.setFont(new Font("Arial", Font.ITALIC, 30));
        To_Do_List.setForeground(Color.BLACK);
        add(To_Do_List);

        setLayout(null);
        JButton adicionar_tarefa = new JButton("Adicionar tarefa");
        adicionar_tarefa.setBounds(30, 100, 200, 50);
        adicionar_tarefa.setFont(new Font("Arial", Font.BOLD, 18));
        adicionar_tarefa.setForeground(Color.BLACK);
        adicionar_tarefa.setBackground(Color.GRAY);
        add(adicionar_tarefa);

        JButton listar_tarefas = new JButton("Listar tarefas");
        listar_tarefas.setBounds(260, 100, 200, 50);
        listar_tarefas.setFont(new Font("Arial", Font.BOLD, 18));
        listar_tarefas.setForeground(Color.BLACK);
        listar_tarefas.setBackground(Color.GRAY);
        add(listar_tarefas);

        JButton editar_tarefa = new JButton("Editar tarefa");
        editar_tarefa.setBounds(490, 100, 200, 50);
        editar_tarefa.setFont(new Font("Arial", Font.BOLD, 18));
        editar_tarefa.setForeground(Color.BLACK);
        editar_tarefa.setBackground(Color.GRAY);
        add(editar_tarefa);

        JButton remover_tarefa = new JButton("Remover tarefa");
        remover_tarefa.setBounds(720, 100, 200, 50);
        remover_tarefa.setFont(new Font("Arial", Font.BOLD, 18));
        remover_tarefa.setForeground(Color.BLACK);
        remover_tarefa.setBackground(Color.GRAY);
        add(remover_tarefa);

        JButton mudar_estado_da_tarefa = new JButton("Mudar estado da tarefa");
        mudar_estado_da_tarefa.setBounds(130, 200, 200, 50);
        mudar_estado_da_tarefa.setFont(new Font("Arial", Font.BOLD, 15));
        mudar_estado_da_tarefa.setForeground(Color.BLACK);
        mudar_estado_da_tarefa.setBackground(Color.GRAY);
        add(mudar_estado_da_tarefa);

        JButton salvar_tarefas = new JButton("Salvar tarefas");
        salvar_tarefas.setBounds(380, 200, 200, 50);
        salvar_tarefas.setFont(new Font("Arial", Font.BOLD, 18));
        salvar_tarefas.setForeground(Color.BLACK);
        salvar_tarefas.setBackground(Color.GRAY);
        add(salvar_tarefas);

        JButton carregar_tarefas = new JButton("Carregar tarefas");
        carregar_tarefas.setBounds(635, 200, 200, 50);
        carregar_tarefas.setFont(new Font("Arial", Font.BOLD, 18));
        carregar_tarefas.setForeground(Color.BLACK);
        carregar_tarefas.setBackground(Color.GRAY);
        add(carregar_tarefas);

        JButton defenir_lembrete = new JButton("Defenir Lembrete");
        defenir_lembrete.setBounds(380, 300, 200, 50);
        defenir_lembrete.setFont(new Font("Arial", Font.BOLD, 18));
        defenir_lembrete.setForeground(Color.BLACK);
        defenir_lembrete.setBackground(Color.GRAY);
        add(defenir_lembrete);

        JButton sair = new JButton("Sair");
        sair.setBounds(750, 900, 200, 50);
        sair.setFont(new Font("Arial", Font.BOLD, 18));
        sair.setForeground(Color.BLACK);
        sair.setBackground(Color.GRAY);
        add(sair);

        adicionar_tarefa.addActionListener(this::acao_adicionar);
        listar_tarefas.addActionListener(this::acao_listar);
        editar_tarefa.addActionListener(this::acao_editar);
        remover_tarefa.addActionListener(this::acao_remover);
        mudar_estado_da_tarefa.addActionListener(this::acao_mudar);
        salvar_tarefas.addActionListener(this::acao_salvar);
        carregar_tarefas.addActionListener(this::acao_carregar);
        defenir_lembrete.addActionListener(this::acao_defenir_lembrete);
        sair.addActionListener(this::acao_sair);
    }
    private void acao_sair(ActionEvent actionEvent) {
        System.exit(0);
    }
    private void acao_defenir_lembrete(ActionEvent actionEvent) {
        String nomeTarefa = JOptionPane.showInputDialog("Digite o nome da tarefa:");
        if (nomeTarefa == null || nomeTarefa.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome da tarefa não fornecido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean taskExists = false;
        for (Tarefas t : tarefas) {
            if (t.getTitulo().equals(nomeTarefa)) {
                taskExists = true;
                break;
            }
        }

        if (!taskExists) {
            JOptionPane.showMessageDialog(null, "A tarefa com o nome '" + nomeTarefa + "' não existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String fraseAviso = JOptionPane.showInputDialog("Digite a frase de aviso para a tarefa:");
        if (fraseAviso == null || fraseAviso.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Frase de aviso não fornecida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String horaStr = JOptionPane.showInputDialog("Digite a hora do lembrete (HH:mm):");

            if (horaStr == null || horaStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Hora do lembrete não fornecida.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalTime horaLembrete = LocalTime.parse(horaStr);

            long delay = horaLembrete.toNanoOfDay() - LocalTime.now().toNanoOfDay();

            if (delay <= 0) {
                JOptionPane.showMessageDialog(null, "A hora do lembrete deve ser no futuro.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            timer = new Timer((int) (delay / 1_000_000), evt -> {
                exibirNotificacao("Lembrete para a tarefa '" + nomeTarefa + "': " + fraseAviso);
                timer.stop();
            });
            timer.setRepeats(false);
            timer.start();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Formato de hora inválido. Use HH:mm.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
    private void exibirNotificacao(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Lembrete", JOptionPane.INFORMATION_MESSAGE);
    }
    private void acao_carregar(ActionEvent actionEvent) {
        tarefas = Persistencia_Dados.carregarTarefas();
    }
    private void acao_salvar(ActionEvent actionEvent) {
        Persistencia_Dados.salvarTarefas(tarefas);
    }
    private void acao_mudar(ActionEvent actionEvent) {
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
    private void acao_remover(ActionEvent actionEvent) {
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
    private void acao_editar(ActionEvent actionEvent) {
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
                String novoTitulo = JOptionPane.showInputDialog("Digite o novo título da tarefa:");
                t.setTitulo(novoTitulo);
                break;
            case 1:
                String novaDescricao = JOptionPane.showInputDialog("Digite a nova descrição da tarefa:");
                t.setDescricao(novaDescricao);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Saindo...", "Editar Tarefa", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
    private void acao_listar(ActionEvent actionEvent) {
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
    private void acao_adicionar(ActionEvent actionEvent) {
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