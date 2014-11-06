/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;
import java.io.Serializable;

/**
 * Enumerated for upgrade types.
 * @author lsmoore
 */
public enum UpgradeType implements Serializable {
    attackUpgrade,
    healthUpgrade,
    speedUpgrade,
    capacityUpgrade
}
