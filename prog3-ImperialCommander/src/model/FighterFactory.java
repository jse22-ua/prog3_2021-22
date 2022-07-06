package model;

import java.lang.reflect.*;
import java.util.Objects;

import model.fighters.*;

/**
 * Practica 3
 * @author Judit Serrano Espinosa,74379872B
 *
 */

public class FighterFactory{
	public static Fighter createFighter(String type, Ship mother) {
		Objects.requireNonNull(type);
		Objects.requireNonNull(mother);
		Fighter f;
				Class<?> clase;
				try {
					clase = Class.forName("model.fighters." + type);
					Constructor<?> metodo;
					metodo = clase.getDeclaredConstructor(Ship.class);
					f = (Fighter) metodo.newInstance(mother);
					return f;
				} catch (NoClassDefFoundError |ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (NoSuchMethodException | SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return null;
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					return null;
				}
			}
}
