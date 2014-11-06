/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author shiro_000
 */
public class ShipyardPageController implements Initializable {

    private ArrayList<Ship> shipList;
    private ShipYard shipYard;
    private ToggleGroup shipGroup = new ToggleGroup();
    private ToggleGroup upgradeGroup = new ToggleGroup();
    private Character cha = Singleton.getCharacter();
    @FXML
    private Label titan1;
    @FXML
    private Label titan2;
    @FXML
    private Label titan3;
    @FXML
    private Label banshee1;
    @FXML
    private Label banshee2;
    @FXML
    private Label banshee3;
    @FXML
    private Label rusty1;
    @FXML
    private Label rusty2;
    @FXML
    private Label serenity1;
    @FXML
    private Label serenity2;
    @FXML
    private Label shipyardHeader;
    @FXML
    private RadioButton banshee2rad;
    @FXML
    private RadioButton banshee3rad;
    @FXML
    private RadioButton rusty1rad;
    @FXML
    private RadioButton rusty2rad;
    @FXML
    private RadioButton serenity1rad;
    @FXML
    private RadioButton serenity2rad;
    @FXML
    private RadioButton titan2rad;
    @FXML
    private RadioButton titan3rad;
    @FXML
    private RadioButton banshee1rad;
    @FXML
    private RadioButton titan1rad;
    @FXML
    private Tab upgradeTab;
    @FXML
    private RadioButton capacityRad;
    @FXML
    private RadioButton neuronTorpedos;
    @FXML
    private RadioButton plasmaBlasters;
    @FXML
    private RadioButton deathStarLaser;
    @FXML
    private RadioButton nitrogenBooster;
    @FXML
    private RadioButton fluxCapacitor;
    @FXML
    private RadioButton warpGenerator;
    @FXML
    private RadioButton gravityShield;
    @FXML
    private RadioButton neuronField;
    @FXML
    private RadioButton unobtanium;
    @FXML
    private Label shipLabel;
    @FXML
    private Label coinShipLabel;
    @FXML
    private Label coinLabel2;
    @FXML
    private Label skillLabelShipyard;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        shipList = Ships.getList();
        shipYard = currentP().getShipYard();
        coinLabel2.setText(Integer.toString(cha.getInventory().getBalance()));
        // inventoryLabel.setText(Integer.toString(cha.getInventory()
        //.totalItemCount()) + " / "
        //      + Integer.toString(cha.getInventory().getCapacity()));
        coinShipLabel.setText("Coins: ");

        shipyardHeader.setText("Name\t\tPrice\t\tMLP\t\tWeight\t\tAttack\t  "
                + "Speed\t   FuelCap\tItemCap\tHealth");
        titan1.setText(shipList.get(0).getName() + "\t\t"
                + shipList.get(0).toString());
        titan2.setText(shipList.get(1).getName()
                + "\t\t" + shipList.get(1).toString());
        titan3.setText(shipList.get(2).getName()
                + "\t\t" + shipList.get(2).toString());

        banshee1.setText(shipList.get(3).getName()
                + "\t" + shipList.get(3).toString());
        banshee2.setText(shipList.get(4).getName()
                + "\t" + shipList.get(4).toString());
        banshee3.setText(shipList.get(5).getName()
                + "\t" + shipList.get(5).toString());

        rusty1.setText(shipList.get(6).getName()
                + "\t\t" + shipList.get(6).toString());
        rusty2.setText(shipList.get(7).getName()
                + "\t\t" + shipList.get(7).toString());

        serenity1.setText(shipList.get(8).getName()
                + "\t" + shipList.get(8).toString());
        serenity2.setText(shipList.get(9).getName()
                + "\t" + shipList.get(9).toString());

        shipLabel.setText(Singleton.getCharacter().getShip().getName()
                + "\t" + Singleton.getCharacter().getShip().toString());

        //adds the radio buttons to a toggle group
        titan1rad.setToggleGroup(shipGroup);
        titan2rad.setToggleGroup(shipGroup);
        titan3rad.setToggleGroup(shipGroup);
        banshee1rad.setToggleGroup(shipGroup);
        banshee2rad.setToggleGroup(shipGroup);
        banshee3rad.setToggleGroup(shipGroup);
        rusty1rad.setToggleGroup(shipGroup);
        rusty2rad.setToggleGroup(shipGroup);
        serenity1rad.setToggleGroup(shipGroup);
        serenity2rad.setToggleGroup(shipGroup);

        capacityRad.setToggleGroup(upgradeGroup);
        neuronTorpedos.setToggleGroup(upgradeGroup);
        plasmaBlasters.setToggleGroup(upgradeGroup);
        deathStarLaser.setToggleGroup(upgradeGroup);
        nitrogenBooster.setToggleGroup(upgradeGroup);
        fluxCapacitor.setToggleGroup(upgradeGroup);
        warpGenerator.setToggleGroup(upgradeGroup);
        gravityShield.setToggleGroup(upgradeGroup);
        neuronField.setToggleGroup(upgradeGroup);
        unobtanium.setToggleGroup(upgradeGroup);

        skillLabelShipyard.setText("Upgrades Left: "
                + cha.getShip().getUpgradesLeft()
                + "    Capacity: " + cha.getShip().getCapacity()
                + "    Attack: " + cha.getShip().getAttack()
                + "    Speed: " + cha.getShip().getSpeed() + "    Health: "
                + cha.getShip().getMaxHealth());
    }

    /**
     * Handles when the refuel button is pressed.
     *
     * @param event
     *
     *
     */
    @FXML
    private void refuelButtonClicked(ActionEvent event) {
        Action response = Dialogs.create()
                .title("Confirm Refuel")
                .masthead("It will cost "
                        + shipYard.costOfRefuelCompletely(cha.getShip())
                        + " to refuel your ship.")
                .message("Do you wish to confirm your purchase?")
                .showConfirm();
        String message = null;
        if (response == Dialog.Actions.YES) {
            message = shipYard.refuelCompletely();
        }
        if (message != null) {
            dialog(message);
        }
        //update coin balance
        coinLabel2.setText(Integer.toString(cha.getInventory().getBalance()));

    }

    /**
     * Handles when the repair button is pressed.
     *
     * @param event
     *
     */
    @FXML
    private void repairButtonClicked(ActionEvent event) {
        Action response = Dialogs.create()
                .title("Confirm Repair")
                .masthead("It will cost " + shipYard.getRepairCost()
                        + " to repair your ship.")
                .message("Do you wish to confirm your purchase?")
                .showConfirm();

        if (response == Dialog.Actions.YES) {
            shipYard.repairShip();
        }
        //update coin balance
        coinLabel2.setText(Integer.toString(cha.getInventory().getBalance()));
    }

    /**
     * Handles when the upgrade button is pressed.
     * @param event
     */
    @FXML
    private void upgradeButtonClicked(ActionEvent event) {
        if ((!upgradeTab.isSelected())) {
            Toggle selected = shipGroup.getSelectedToggle();
            if (selected == null) {
                dialog("Please select a ship to upgrade.");
            } else {
                Action response = Dialogs.create()
                        .title("Upgrade Ship")
                        .masthead("You will lose your current ship "
                                + "and receive half its value.")
                        .message("Do you wish to confirm your purchase?")
                        .showConfirm();
                //selected.equals(id of button in question)
                //do things
                String message = null;
                if (response == Dialog.Actions.YES) {
                    if (selected.equals(titan1rad)) {
                        message = shipYard.buyShip(shipList.get(0));
                        titan1rad.setSelected(false);
                    } else if (selected.equals(titan2rad)) {
                        message = shipYard.buyShip(shipList.get(1));
                        titan2rad.setSelected(false);
                    } else if (selected.equals(titan3rad)) {
                        message = shipYard.buyShip(shipList.get(2));
                        titan3rad.setSelected(false);
                    } else if (selected.equals(banshee1rad)) {
                        message = shipYard.buyShip(shipList.get(3));
                        banshee1rad.setSelected(false);
                    } else if (selected.equals(banshee2rad)) {
                        message = shipYard.buyShip(shipList.get(4));
                        banshee2rad.setSelected(false);
                    } else if (selected.equals(banshee3rad)) {
                        message = shipYard.buyShip(shipList.get(5));
                        banshee3rad.setSelected(false);
                    } else if (selected.equals(rusty1rad)) {
                        message = shipYard.buyShip(shipList.get(6));
                        rusty1rad.setSelected(false);
                    } else if (selected.equals(rusty2rad)) {
                        message = shipYard.buyShip(shipList.get(7));
                        rusty2rad.setSelected(false);
                    } else if (selected.equals(serenity1rad)) {
                        message = shipYard.buyShip(shipList.get(8));
                        serenity1rad.setSelected(false);
                    } else if (selected.equals(serenity2rad)) {
                        message = shipYard.buyShip(shipList.get(9));
                        serenity2rad.setSelected(false);
                    }
                    if (message != null) {
                        dialog(message);
                    }
                }
            }
        } else {
            Toggle selected2 = upgradeGroup.getSelectedToggle();
            if (selected2 == null) {
                dialog("Please select an upgrade to add.");
            } else {
                Action response = Dialogs.create()
                        .title("Add Upgrades")
                        .masthead("You have chosen to add an upgrade.")
                        .message("Do you wish to confirm your purchase?")
                        .showConfirm();
                //selected.equals(id of button in question)
                //do things
                String message = null;
                if (response == Dialog.Actions.YES) {
                    if (selected2.equals(neuronTorpedos)) {
                        message = currentP().getShipYard().buyNeuronTorpedos();
                        neuronTorpedos.setSelected(false);
                    } else if (selected2.equals(plasmaBlasters)) {
                        message = currentP().getShipYard().buyPlasmaBlasters();
                        plasmaBlasters.setSelected(false);
                    } else if (selected2.equals(deathStarLaser)) {
                        message = currentP().getShipYard().buyDeathStarLaser();
                        deathStarLaser.setSelected(false);
                    } else if (selected2.equals(nitrogenBooster)) {
                        message = currentP().getShipYard().buyNitrogenBooster();
                        nitrogenBooster.setSelected(false);
                    } else if (selected2.equals(fluxCapacitor)) {
                        message = currentP().getShipYard().buyFluxCapacitor();
                        fluxCapacitor.setSelected(false);
                    } else if (selected2.equals(warpGenerator)) {
                        message = currentP().getShipYard().buyWarpGenerator();
                        warpGenerator.setSelected(false);
                    } else if (selected2.equals(gravityShield)) {
                        message = currentP().getShipYard().buyGravityShield();
                        gravityShield.setSelected(false);
                    } else if (selected2.equals(neuronField)) {
                        message = currentP().getShipYard().buyNeuronField();
                        neuronField.setSelected(false);
                    } else if (selected2.equals(unobtanium)) {
                        message = currentP().getShipYard().buyUnobtanium();
                        unobtanium.setSelected(false);
                    } else if (selected2.equals(capacityRad)) {
                        message = currentP().getShipYard().buyIncreasedCargo();
                        capacityRad.setSelected(false);
                    }
                    if (message != null) {
                        dialog(message);
                    }
                }
            }
        }

        //updates labels
        skillLabelShipyard.setText("Upgrades Left: "
                + cha.getShip().getUpgradesLeft()
                + "    Capacity: " + cha.getShip().getCapacity()
                + "    Attack: " + cha.getShip().getAttack()
                + "    Speed: " + cha.getShip().getSpeed() + "    Health: "
                + cha.getShip().getMaxHealth());
        coinLabel2.setText(Integer.toString(cha.getInventory().getBalance()));
        shipLabel.setText(Singleton.getCharacter().getShip().getName()
                + "\t" + Singleton.getCharacter().getShip().toString());
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
