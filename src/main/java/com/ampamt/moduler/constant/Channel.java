package com.ampamt.moduler.constant;

public enum Channel {

	UMOBILE("GOPAYZ");
	
	private String channel;
	
	Channel(String channel) {
		this.channel = channel;
	}
	
	public String getChannel() {
		return channel;
	}

	public class Constant {
		private Constant() {
		}
		public static final String CHANNEL_HEADER = "Channel";
	}
}
