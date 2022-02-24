package quali.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import quali.Main;

public class Context {

	private static final String ENCODING_CHARSET = "ISO-8859-1";

	public static final String SAVES_LOCATION =  System.getProperty("user.dir") + "/save.json";

	private User loggedUser;

	private UsersList usersList;

	private String passIsForgot;

	private static Context instance;

	public static Context getInstance(){
		if (instance == null) {
			instance = new Context();
		}
		return instance;
	}

	private Context() {
		this.usersList = new UsersList();
		this.passIsForgot = "";
	}

	public String getPasswordIsForgot() {
		return this.passIsForgot;
	}

	public void setPasswordIsForgot(String bool) {
		this.passIsForgot = bool;
	}

	public ListProperty<User> getUsersList() {
		ListProperty<User> users = new SimpleListProperty<>(FXCollections.observableArrayList());
		for(User user : usersList.getUsersList()) {
			users.add(user);
		}
		return users;
	}

	public void setUsersList(UsersList usersList) {
		this.usersList = usersList;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	/**
	 * Permet de charger la dernière sauvegarde depuis un fichier json
	 */
	public void load() {
		File file = new File(SAVES_LOCATION);
		if (file.exists()) {
			GsonBuilder builder = new GsonBuilder();
		    Gson gson = builder.create();

			try {
				Reader reader = Files.newBufferedReader(Paths.get(SAVES_LOCATION), Charset.forName(ENCODING_CHARSET));
				this.usersList = gson.fromJson(reader, UsersList.class);
			} catch (IOException e) {
				Main.LOGGER.severe("Impossible de lire le fichier de sauvegarde");
			}
		}
	}

	/**
	 * Permet de sauvegarder les données dans un fichier json
	 */
	public void save() {
		File file = new File(SAVES_LOCATION);
		if (!file.exists()) {
			try {
				if(!file.createNewFile()) {
					Main.LOGGER.severe("Impossible de créer le fichier de sauvegarde");
				}
			} catch (IOException e) {
				Main.LOGGER.severe("Impossible de créer le fichier de sauvegarde");
			}
		}
		try(FileWriter fos = new FileWriter(file)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			fos.write(gson.toJson(this.usersList));
		} catch (Exception e) {
			Main.LOGGER.severe("Impossible d'écrire le fichier de sauvegarde");
		}
	}

	/**
	 * Retourne le user ayant la combinaison email / mot de passe en paramètre, retourne null sinon
	 * @param email		l'email de l'utilisateur
	 * @param password	Le mot de passe de l'utilisateur
	 * @return Le User ou null si n'existe pas
	 */
	public User findUser(String email, String password) {
		List<User> users = Context.getInstance().getUsersList();
		for(User user : users) {
			if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Retourne le user ayant l'email en paramètre, retourne null sinon
	 * @param email		l'email de l'utilisateur
	 * @return Le User ou null si n'existe pas
	 */
	public User findUser(String email) {
		List<User> users = Context.getInstance().getUsersList();
		for(User user : users) {
			if(user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Retourne un mot de passe temporaire pour l'utilisateur en paramètre
	 * @param email	L'email de l'utilisateur
	 * @return	Le nouveau mot de passe ou null si le compte n'existe pas
	 */
	public String recovery(String email) {
		List<User> users = Context.getInstance().getUsersList();
		for(User user : users) {
			if(user.getEmail().equals(email)) {
				return user.getPassword();
			}
		}
		return null;
	}
}
