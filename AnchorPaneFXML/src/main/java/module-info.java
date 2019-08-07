module br.edu.ifsc.canoinhas.projeto2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.edu.ifsc.canoinhas.projeto2 to javafx.fxml;
    exports br.edu.ifsc.canoinhas.projeto2;
}