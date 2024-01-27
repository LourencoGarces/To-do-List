public class Tarefas {
    String titulo;
    String descricao;
    String estado;
    public Tarefas(String titulo, String descricao, String estado) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.estado = estado;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getEstado() {
        return estado;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String toString() {
        return "Título: " + titulo + "\nDescrição: " + descricao + "\nEstado: " + estado;
    }
}
