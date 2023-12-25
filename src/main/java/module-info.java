module com.vtnq.smartmoney {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires MaterialFX;
    requires VirtualizedFX;
    requires java.sql;

    opens com.vtnq.smartmoney to javafx.fxml;
    exports com.vtnq.smartmoney;
}