/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bla.controlller.impl;

import dto.RightDTO;
import exceptions.rightsnotfound;

/**
 *
 * @author joker
 */
public interface RightController {
         public RightDTO findRightsById(int id) throws rightsnotfound;
}
