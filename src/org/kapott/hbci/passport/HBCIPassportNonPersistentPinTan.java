/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kapott.hbci.passport;


/**
 *
 * @author alex
 */
public class HBCIPassportNonPersistentPinTan extends HBCIPassportPinTan {

	// TODO: Um Passport daten zu bearbeiten einfach setter-Methoden der
	// Parent-Klasse aufrufen und dann saveChanges()-Methode aufrufen!

	public HBCIPassportNonPersistentPinTan(Object initObject) {
	    this(initObject,0);
	}

	public HBCIPassportNonPersistentPinTan(Object init, int dummy) {
		super(init, dummy);
	}

	@Override
	public void saveChanges() {
		// Intentionally empty
	}

}
