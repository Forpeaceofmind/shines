package edu.training.web.controller.concrete;

import java.util.HashMap;
import java.util.Map;

import edu.training.web.controller.concrete.impl.AddNews;
import edu.training.web.controller.concrete.impl.DoAuth;
import edu.training.web.controller.concrete.impl.DoEdit;
import edu.training.web.controller.concrete.impl.DoLogout;
import edu.training.web.controller.concrete.impl.DoRegistration;
import edu.training.web.controller.concrete.impl.EditNews;
import edu.training.web.controller.concrete.impl.GoToAddingNewsPage;
import edu.training.web.controller.concrete.impl.GoToIndexPage;
import edu.training.web.controller.concrete.impl.GoToMainPage;
import edu.training.web.controller.concrete.impl.GoToSignInPage;
import edu.training.web.controller.concrete.impl.Localizations;
import edu.training.web.controller.concrete.impl.GoToRegistrationPage;
import edu.training.web.controller.concrete.impl.NoSuchCommand;
import edu.training.web.controller.concrete.impl.ShowBusinessNews;
import edu.training.web.controller.concrete.impl.ShowGoodNews;
import edu.training.web.controller.concrete.impl.ShowLiveNews;
import edu.training.web.controller.concrete.impl.ShowPoliticalNews;
import edu.training.web.controller.concrete.impl.ShowSportNews;
import edu.training.web.controller.concrete.impl.ShowTheWeather;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.DO_LOGOUT, new DoLogout());
		commands.put(CommandName.DO_AUTH, new DoAuth());
		commands.put(CommandName.DO_EDIT, new DoEdit());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.ADD_NEWS, new AddNews());
		commands.put(CommandName.EDIT_NEWS, new EditNews());

		commands.put(CommandName.GO_TO_INDEX_PAGE, new GoToIndexPage());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.GO_TO_SIGN_IN_PAGE, new GoToSignInPage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.GO_TO_ADDING_NEWS_PAGE, new GoToAddingNewsPage());
		
		commands.put(CommandName.SHOW_BUSINESS_NEWS, new ShowBusinessNews());
		commands.put(CommandName.SHOW_GOOD_NEWS, new ShowGoodNews());
		commands.put(CommandName.SHOW_THE_WEATHER, new ShowTheWeather());
		commands.put(CommandName.SHOW_LIVE_NEWS, new ShowLiveNews());
		commands.put(CommandName.SHOW_POLITICAL_NEWS, new ShowPoliticalNews());
		commands.put(CommandName.SHOW_SPORT_NEWS, new ShowSportNews());

		commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		commands.put(CommandName.LOCALIZATIONS, new Localizations());
	}

	public Command takeCommand(String userCommand) {
		CommandName commandName;
		Command command;

		try {
			commandName = CommandName.valueOf(userCommand.toUpperCase());
			command = commands.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}

		return command;
	}

}
