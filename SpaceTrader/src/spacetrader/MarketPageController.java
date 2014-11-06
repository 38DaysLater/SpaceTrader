/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author shiro_000
 */
public class MarketPageController implements Initializable {

    Character cha = Singleton.getCharacter();
    String success = null;
    @FXML
    private Button bcButt;
    @FXML
    private TextField bfoodText, bwatText, bmedText;
    @FXML
    private Label bfoodP, bWatP, bmedP, bfoodQuan, bwatQuan, bmedQuan;
    @FXML
    private Button scButt;
    @FXML
    private TextField sfoodText, swatText, smedText, sFursTxt, sOreTxt, sGamTxt;
    @FXML
    private Label sfoodP, swatP, smedP, sfoodQuan, swatQuan, smedQuan;
    @FXML
    private Button bEButt;
    @FXML
    private Label bFireP, bMacP, bRobP;
    @FXML
    private Label bFireQ, bMacQ, bRobQ;
    @FXML
    private Button sEButt;
    @FXML
    private TextField sFireTxt, sMacTxt, sRobTxt, bFursTxt, bOreTxt, bGamTxt,
            bFireTxt, bMacTxt, bRobTxt;
    @FXML
    private Label sFireP, sMacP, sRobP, sFireQ, sMacQ, sRobQ;
    @FXML
    private Button bMButt;
    @FXML
    private Label bFursP, bOreP, bGamP, bFursQ, bOreQ, bGamQ;
    @FXML
    private Button sMButt;
    @FXML
    private Label sFursP, sOreP, sGamP, sFursQ, sOreQ, sGamQ;
    @FXML
    private Label coinLabel;
    @FXML
    private Label inventoryCapLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//sets initial coin amount from player inventory
        coinLabel.setText(Integer.toString(
                Singleton.getCharacter().getInventory().getBalance()));
        inventoryCapLabel.setText("Capacity: "
                + Singleton.getCharacter().getInventory().totalItemCount()
                + "/" + Singleton.getCharacter().getInventory().getCapacity());

        //sets all labels for item quantity from market and player inventory
        //if quantity for planet it -1 then the planet
        //can't buy or sell that item
        String bQuan = Integer.toString(
                currentP().getMarket().getInventory().getItemCount("Food"));
        String bPrice = Integer.toString(
                currentP().getMarket().getInventory().getItemPrice("Food"));
        String sQuan = Integer.toString(
                Singleton.getCharacter().getInventory().getItemCount("Food"));
        if (bQuan.equals("-1")) {
            bfoodP.setText("N/A");
            sfoodP.setText("N/A");
            bfoodQuan.setText("N/A");
            sfoodQuan.setText(sQuan);
            sfoodText.setVisible(false);
            bfoodText.setVisible(false);
        } else {
            bfoodP.setText(bPrice);
            sfoodP.setText(bPrice);
            bfoodQuan.setText(bQuan);
            sfoodQuan.setText(sQuan);
            sfoodText.setVisible(true);
            bfoodText.setVisible(true);
        }
        bQuan = Integer.toString(
                currentP().getMarket().getInventory().getItemCount("Water"));
        bPrice = Integer.toString(
                currentP().getMarket().getInventory().getItemPrice("Water"));
        sQuan = Integer.toString(
                Singleton.getCharacter().getInventory().getItemCount("Water"));
        if (bQuan.equals("-1")) {
            bWatP.setText("N/A");
            swatP.setText("N/A");
            bwatQuan.setText("N/A");
            swatQuan.setText(sQuan);
            swatText.setVisible(false);
            bwatText.setVisible(false);
        } else {
            bWatP.setText(bPrice);
            swatP.setText(bPrice);
            bwatQuan.setText(bQuan);
            swatQuan.setText(sQuan);
            swatText.setVisible(true);
            bwatText.setVisible(true);
        }
        bQuan = Integer.toString(
                currentP().getMarket().getInventory().getItemCount("Medicine"));
        bPrice = Integer.toString(
                currentP().getMarket().getInventory().getItemPrice("Medicine"));
        sQuan = Integer.toString(Singleton.getCharacter().getInventory()
                .getItemCount("Medicine"));
        if (bQuan.equals("-1")) {
            bmedP.setText("N/A");
            smedP.setText("N/A");
            bmedQuan.setText("N/A");
            smedQuan.setText(sQuan);
            smedText.setVisible(false);
            bmedText.setVisible(false);
        } else {
            bmedP.setText(bPrice);
            smedP.setText(bPrice);
            bmedQuan.setText(bQuan);
            smedQuan.setText(sQuan);
            smedText.setVisible(true);
            bmedText.setVisible(true);
        }
        bQuan = Integer.toString(currentP().getMarket().getInventory()
                .getItemCount("Firearms"));
        bPrice = Integer.toString(currentP().getMarket().getInventory()
                .getItemPrice("Firearms"));
        sQuan = Integer.toString(Singleton.getCharacter().getInventory()
                .getItemCount("Firearms"));
        if (bQuan.equals("-1")) {
            bFireP.setText("N/A");
            sFireP.setText("N/A");
            bFireQ.setText("N/A");
            sFireQ.setText(sQuan);
            sFireTxt.setVisible(false);
            bFireTxt.setVisible(false);
        } else {
            bFireP.setText(bPrice);
            sFireP.setText(bPrice);
            bFireQ.setText(bQuan);
            sFireQ.setText(sQuan);
            sFireTxt.setVisible(true);
            bFireTxt.setVisible(true);
        }
        bQuan = Integer.toString(currentP().getMarket().getInventory()
                .getItemCount("Machines"));
        bPrice = Integer.toString(currentP().getMarket().getInventory()
                .getItemPrice("Machines"));
        sQuan = Integer.toString(Singleton.getCharacter().getInventory()
                .getItemCount("Machines"));
        if (bQuan.equals("-1")) {
            bMacP.setText("N/A");
            sMacP.setText("N/A");
            bMacQ.setText("N/A");
            sMacQ.setText(sQuan);
            sMacTxt.setVisible(false);
            bMacTxt.setVisible(false);
        } else {
            bMacP.setText(bPrice);
            sMacP.setText(bPrice);
            bMacQ.setText(bQuan);
            sMacQ.setText(sQuan);
            sMacTxt.setVisible(true);
            bMacTxt.setVisible(true);
        }
        bQuan = Integer.toString(currentP().getMarket().getInventory()
                .getItemCount("Robots"));
        bPrice = Integer.toString(currentP().getMarket().getInventory()
                .getItemPrice("Robots"));
        sQuan = Integer.toString(Singleton.getCharacter().getInventory()
                .getItemCount("Robots"));
        if (bQuan.equals("-1")) {
            bRobP.setText("N/A");
            sRobP.setText("N/A");
            bRobQ.setText("N/A");
            sRobQ.setText(sQuan);
            sRobTxt.setVisible(false);
            bRobTxt.setVisible(false);
        } else {
            bRobP.setText(bPrice);
            sRobP.setText(bPrice);
            bRobQ.setText(bQuan);
            sRobQ.setText(sQuan);
            sRobTxt.setVisible(true);
            bRobTxt.setVisible(true);
        }
        bQuan = Integer.toString(currentP().getMarket().getInventory()
                .getItemCount("Furs"));
        bPrice = Integer.toString(currentP().getMarket().getInventory()
                .getItemPrice("Furs"));
        sQuan = Integer.toString(Singleton.getCharacter().getInventory()
                .getItemCount("Furs"));
        if (bQuan.equals("-1")) {
            bFursP.setText("N/A");
            sFursP.setText("N/A");
            bFursQ.setText("N/A");
            sFursQ.setText(sQuan);
            sFursTxt.setVisible(false);
            bFursTxt.setVisible(false);
        } else {
            bFursP.setText(bPrice);
            sFursP.setText(bPrice);
            bFursQ.setText(bQuan);
            sFursQ.setText(sQuan);
            sFursTxt.setVisible(true);
            bFursTxt.setVisible(true);
        }
        bQuan = Integer.toString(currentP().getMarket().getInventory()
                .getItemCount("Ore"));
        bPrice = Integer.toString(currentP().getMarket().getInventory()
                .getItemPrice("Ore"));
        sQuan = Integer.toString(Singleton.getCharacter().getInventory()
                .getItemCount("Ore"));
        if (bQuan.equals("-1")) {
            bOreP.setText("N/A");
            sOreP.setText("N/A");
            bOreQ.setText("N/A");
            sOreQ.setText(sQuan);
            sOreTxt.setVisible(false);
            bOreTxt.setVisible(false);
        } else {
            bOreP.setText(bPrice);
            sOreP.setText(bPrice);
            bOreQ.setText(bQuan);
            sOreQ.setText(sQuan);
            sOreTxt.setVisible(true);
            bOreTxt.setVisible(true);
        }
        bQuan = Integer.toString(currentP().getMarket().getInventory()
                .getItemCount("Games"));
        bPrice = Integer.toString(currentP().getMarket().getInventory()
                .getItemPrice("Games"));
        sQuan = Integer.toString(Singleton.getCharacter().getInventory()
                .getItemCount("Games"));
        if (bQuan.equals("-1")) {
            bGamP.setText("N/A");
            sGamP.setText("N/A");
            bGamQ.setText("N/A");
            sGamQ.setText(sQuan);
            sGamTxt.setVisible(false);
            bGamTxt.setVisible(false);
        } else {
            bGamP.setText(bPrice);
            sGamP.setText(bPrice);
            bGamQ.setText(bQuan);
            sGamQ.setText(sQuan);
            sGamTxt.setVisible(true);
            bGamTxt.setVisible(true);
        }
    }

    /**
     * Handles when the ok button is clicked inside the buy consumables section
     * of the market.
     *
     * @param event
     */
    @FXML
    private void buyConHandle(ActionEvent event) {
        try {
            if (!bfoodText.getText().isEmpty()) {
                success = currentP().getMarket().sellItem("Food", cha,
                        Integer.parseInt(bfoodText.getText()));
                if (success != null) {
                    //dialog box
                    dialog(success);
                }
            }
            if (!bwatText.getText().isEmpty()) {
                success = currentP().getMarket().sellItem("Water", cha,
                        Integer.parseInt(bwatText.getText()));
                if (success != null) {
                    //dialog box
                    dialog(success);
                }
            }
            if (!bmedText.getText().isEmpty()) {
                success = currentP().getMarket().sellItem("Medicine", cha,
                        Integer.parseInt(bmedText.getText()));
                if (success != null) {
                    //dialog box
                    dialog(success);
                }
            }
        } catch (NumberFormatException e) {
            dialog("Please enter a valid Integer.");
        }
        //update labels of quantity and coin balance
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        inventoryCapLabel.setText("Capacity: " + cha.getInventory()
                .totalItemCount() + "/" + cha.getInventory().getCapacity());
        sfoodQuan.setText(upSellLab("Food"));
        swatQuan.setText(upSellLab("Water"));
        smedQuan.setText(upSellLab("Medicine"));
        bfoodQuan.setText(upBuyLab("Food"));
        bwatQuan.setText(upBuyLab("Water"));
        bmedQuan.setText(upBuyLab("Medicine"));
        //clear textboxes
        bfoodText.clear();
        bwatText.clear();
        bmedText.clear();
    }

    /**
     * Handles when the ok button is clicked inside the sell consumables section
     * of the market.
     *
     * @param event
     */
    @FXML
    private void sellConHandle(ActionEvent event) {
        try {
            if (!sfoodText.getText().isEmpty()) {
                success = currentP().getMarket().buyItem("Food", cha,
                        Integer.parseInt(sfoodText.getText()));
                if (success != null) {
                    //dialog box
                    dialog(success);
                }
            }
            if (!swatText.getText().isEmpty()) {
                success = currentP().getMarket().buyItem("Water", cha,
                        Integer.parseInt(swatText.getText()));
                if (success != null) {
                    //dialog box
                    dialog(success);
                }
            }
            if (!smedText.getText().isEmpty()) {
                success = currentP().getMarket().buyItem("Medicine", cha,
                        Integer.parseInt(smedText.getText()));
                if (success != null) {
                    //dialog box
                    dialog(success);
                }
            }
        } catch (NumberFormatException e) {
            dialog("Please enter a valid Integer.");
        }
        //update labels of quantity and coin balance
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        inventoryCapLabel.setText("Capacity: " + cha.getInventory()
                .totalItemCount() + "/" + cha.getInventory().getCapacity());
        sfoodQuan.setText(upSellLab("Food"));
        swatQuan.setText(upSellLab("Water"));
        smedQuan.setText(upSellLab("Medicine"));
        bfoodQuan.setText(upBuyLab("Food"));
        bwatQuan.setText(upBuyLab("Water"));
        bmedQuan.setText(upBuyLab("Medicine"));
        //clear textboxes
        sfoodText.clear();
        swatText.clear();
        smedText.clear();
    }

    /**
     * Handles when the ok button is clicked inside the buy equipment section of
     * the market.
     *
     * @param event
     */
    @FXML
    private void buyEquiHandle(ActionEvent event) {
        if (!bFireTxt.getText().isEmpty()) {
            success = currentP().getMarket().sellItem("Firearms", cha,
                    Integer.parseInt(bFireTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!bMacTxt.getText().isEmpty()) {
            success = currentP().getMarket().sellItem("Machines", cha,
                    Integer.parseInt(bMacTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!bRobTxt.getText().isEmpty()) {
            success = currentP().getMarket().sellItem("Robots", cha,
                    Integer.parseInt(bRobTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        //update labels of quantity and coin balance
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        inventoryCapLabel.setText("Capacity: " + cha.getInventory()
                .totalItemCount() + "/" + cha.getInventory().getCapacity());
        sFireQ.setText(upSellLab("Firearms"));
        sMacQ.setText(upSellLab("Machines"));
        sRobQ.setText(upSellLab("Robots"));
        bFireQ.setText(upBuyLab("Firearms"));
        bMacQ.setText(upBuyLab("Machines"));
        bRobQ.setText(upBuyLab("Robots"));
        //clear textboxes
        bFireTxt.clear();
        bMacTxt.clear();
        bRobTxt.clear();
    }

    /**
     * Handles when the ok button is clicked inside the sell equipment section
     * of the market.
     *
     * @param event
     */
    @FXML
    private void sellEquiHandle(ActionEvent event) {
        if (!sFireTxt.getText().isEmpty()) {
            success = currentP().getMarket().buyItem("Firearms", cha,
                    Integer.parseInt(sFireTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!sMacTxt.getText().isEmpty()) {
            success = currentP().getMarket().buyItem("Machines", cha,
                    Integer.parseInt(sMacTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!sRobTxt.getText().isEmpty()) {
            success = currentP().getMarket().buyItem("Robots", cha,
                    Integer.parseInt(sRobTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        //update labels of quantity and coin balance
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        inventoryCapLabel.setText("Capacity: " + cha.getInventory()
                .totalItemCount() + "/" + cha.getInventory().getCapacity());
        sFireQ.setText(upSellLab("Firearms"));
        sMacQ.setText(upSellLab("Machines"));
        sRobQ.setText(upSellLab("Robots"));
        bFireQ.setText(upBuyLab("Firearms"));
        bMacQ.setText(upBuyLab("Machines"));
        bRobQ.setText(upBuyLab("Robots"));
        //clear textboxes
        sFireTxt.clear();
        sMacTxt.clear();
        sRobTxt.clear();
    }

    /**
     * Handles when the ok button is clicked inside the buy misc section of the
     * market.
     *
     * @param event
     */
    @FXML
    private void buyMiscHandle(ActionEvent event) {
        if (!bFursTxt.getText().isEmpty()) {
            success = currentP().getMarket().sellItem("Furs", cha,
                    Integer.parseInt(bFursTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!bOreTxt.getText().isEmpty()) {
            success = currentP().getMarket().sellItem("Ore", cha,
                    Integer.parseInt(bOreTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!bGamTxt.getText().isEmpty()) {
            success = currentP().getMarket().sellItem("Games", cha,
                    Integer.parseInt(bGamTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        //update labels of quantity and coin balance
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        inventoryCapLabel.setText("Capacity: " + cha.getInventory()
                .totalItemCount() + "/" + cha.getInventory().getCapacity());
        sFursQ.setText(upSellLab("Furs"));
        sOreQ.setText(upSellLab("Ore"));
        sGamQ.setText(upSellLab("Games"));
        bFursQ.setText(upBuyLab("Furs"));
        bOreQ.setText(upBuyLab("Ore"));
        bGamQ.setText(upBuyLab("Games"));
        //clear textboxes
        bFursTxt.clear();
        bOreTxt.clear();
        bGamTxt.clear();
    }

    /**
     * Handles when the ok button is clicked inside the sell misc section of the
     * market.
     *
     * @param event
     */
    @FXML
    private void sellMiscHandle(ActionEvent event) {
        if (!sFursTxt.getText().isEmpty()) {
            success = currentP().getMarket().buyItem("Furs", cha,
                    Integer.parseInt(sFursTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!sOreTxt.getText().isEmpty()) {
            success = currentP().getMarket().buyItem("Ore", cha,
                    Integer.parseInt(sOreTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        if (!sGamTxt.getText().isEmpty()) {
            success = currentP().getMarket().buyItem("Games", cha,
                    Integer.parseInt(sGamTxt.getText()));
            if (success != null) {
                //dialog box
                dialog(success);
            }
        }
        //update labels of quantity and coin balance
        coinLabel.setText(Integer.toString(cha.getInventory().getBalance()));
        inventoryCapLabel.setText("Capacity: " + cha.getInventory()
                .totalItemCount() + "/" + cha.getInventory().getCapacity());
        sFursQ.setText(upSellLab("Furs"));
        sOreQ.setText(upSellLab("Ore"));
        sGamQ.setText(upSellLab("Games"));
        bFursQ.setText(upBuyLab("Furs"));
        bOreQ.setText(upBuyLab("Ore"));
        bGamQ.setText(upBuyLab("Games"));
        //clear textboxes
        sFursTxt.clear();
        sOreTxt.clear();
        sGamTxt.clear();
    }

    /**
     * Helper method for getting the current planet.
     *
     * @param item
     * @return Planet
     */
    private Planet currentP() {
        return ((Planet) cha.getCurrentPlanet()[0]);
    }

    /**
     * Helper method for buying items from market.
     *
     * @param item
     * @return String for label update
     */
    private String upSellLab(String item) {
        String up = Integer.toString(cha.getInventory().getItemCount(item));
        if (up.equals("-1")) {
            return "N/A";
        }
        return up;
    }

    /**
     * Helper method for selling items to market.
     *
     * @param item
     * @return String for label update
     */
    private String upBuyLab(String item) {
        String up = Integer.toString(currentP().getMarket().getInventory()
                .getItemCount(item));
        if (up.equals("-1")) {
            return "N/A";
        }
        return up;
    }

    /**
     * Helper method for creating dialogs. takes in string of the message to
     * display
     *
     * @param mess
     */
    private void dialog(String mess) {
        //dialog box start
        Dialogs.create()
                .title("OH NO!")
                .masthead("There was an error with your transaction")
                .message(mess)
                .showWarning();
        //end dialog box
    }
}
