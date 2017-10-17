/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package net.bpelunit.toolsupport.editors.formwidgets;

/**
 * IEditorValidator is used to validate fields in an editor
 */
public interface IEditorValidator {

	/**
	 * Indicates whether this validator is currently enabled.
	 * 
	 * @return true if this validator is enabled.
	 */
	public boolean isEnabled();

	/**
	 * Sets the enabled state of this validator
	 * 
	 * @param enable
	 */
	public void setEnabled(boolean enable);

	/**
	 * Validates the editor's field and updates the error stack if neccesary.
	 * 
	 * @param revalidate
	 * @return true if this form object has a valid entry.
	 */
	public boolean validate(boolean revalidate);

	/**
	 * Validates the an object versus it's model. Message and Severity must be set/unset during this
	 * method.
	 * 
	 * @return true if this form object has a valid entry.
	 */
	public boolean inputValidates();

	/**
	 * Returns the result of the last validation.
	 * 
	 * @return the result of the last validation
	 */
	public boolean markedInvalid();

	/**
	 * Returns a message to the user indicating the problem with the validation
	 * 
	 * @return the message
	 */
	public String getMessage();

	public void setMessage(String message);

	/**
	 * Returns the severity (if any) of the form object's entry's validation result.
	 * 
	 * @return severity
	 */
	public int getSeverity();

	public void setSeverity(int severity);
	/**
	 * Returns the section in which the validator was instantiated
	 * 
	 * @return section
	 */
	// public PDESection getSection();
}
