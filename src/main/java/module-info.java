module com.example.pufighters {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                        requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.pufighters to javafx.fxml;
    exports com.example.pufighters;
    exports com.example.pufighters.Controllers;
    opens com.example.pufighters.Controllers to javafx.fxml;
}