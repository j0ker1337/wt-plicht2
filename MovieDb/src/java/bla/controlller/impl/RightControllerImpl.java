/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bla.controlller.impl;

import dto.RightDTO;
import exceptions.rightsnotfound;
import javax.enterprise.context.Dependent;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author joker
 */

@Named(value = "rightsController")
@Dependent
@RequestScoped
public class RightControllerImpl extends ControllerImpl implements RightController{
    @Override 
             public RightDTO findRightsById(int id) throws rightsnotfound{
                 return super.findRightsById(id);
             }
}
