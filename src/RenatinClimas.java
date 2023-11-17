import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RenatinClimas extends JFrame {

    private JTextArea resultadoTextArea;

    public RenatinClimas() {
        super("Dados Meteorológicos");

        // Configuração do layout
        setLayout(new BorderLayout());

        // Componentes
        resultadoTextArea = new JTextArea();
        resultadoTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultadoTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton buscarButton = new JButton("Buscar Dados Meteorológicos");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarDadosMeteorologicos();
            }
        });

        // Adiciona componentes à tela
        add(scrollPane, BorderLayout.CENTER);
        add(buscarButton, BorderLayout.SOUTH);

        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buscarDadosMeteorologicos() {
        try {
            // Substitua a URL abaixo pela URL real da sua API de dados meteorológicos
            String apiUrl = "https://localhost:8080";

            // Criação do cliente HTTP
            HttpClient httpClient = HttpClient.newHttpClient();

            // Criação da requisição
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            // Envio da requisição e obtenção da resposta
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Exibe os dados na TextArea
            resultadoTextArea.setText(response.body());

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar dados meteorológicos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RenatinClimas();
            }
        });
    }
}