package me.serliunx.chatmanagement.command;

import me.serliunx.chatmanagement.command.subcommand.*;

public class Commands {
    public HelpCommand helpCommand = new HelpCommand();
    public ReloadCommand reloadCommand = new ReloadCommand();
    public PlayerCommand playerCommand = new PlayerCommand();
    public PrivateMessageCommand privateMessageCommand = new PrivateMessageCommand();
    public ConverterCommand converterCommand = new ConverterCommand();
    public ChatCommand chatCommand = new ChatCommand();
    public ListCommand listCommand = new ListCommand();
    public AboutCommand aboutCommand = new AboutCommand();
}
