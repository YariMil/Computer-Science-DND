import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MiniGPT {
	private MarkovPredictor markov;
	private int chainOrder;

	public MiniGPT(String fileName, int chainOrder) {
		markov = new MarkovPredictor();
		this.chainOrder = chainOrder;
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			int charAsInt;
			StringBuilder initialChain = new StringBuilder();
			// Read until the end of the stream (-1 is returned)
			for (int i = 0; i < chainOrder; i++) {
				charAsInt = reader.read();
				char character = (char) charAsInt;
				initialChain.append(character);
			}
			String chain = initialChain.toString();
			while ((charAsInt = reader.read()) != -1) {
				// Cast the integer value to a character
				char character = (char) charAsInt;
				markov.addToMarkov(chain, "" + character);
				chain = chain.substring(1) + character;
			}
		} catch (IOException e) {
			System.err.println("An I/O error occurred: " + e.getMessage());
		}
	}


	public void generateText(String outputFileName, int numChars) {
		markov.getRanEntry();
		// Initially I'm just going to do from the first two "Th"
		// String chain = "Gatsby".substring("Gatsby".length() - chainOrder);
		// Second thing is just picking a random one with no frequency
		String chain = markov.getRanEntry();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
			writer.write(chain);
			for (int i = chain.length(); i < numChars; i++) {
				String predictedState = markov.predictNextState(chain);
				writer.write(predictedState);
				chain = chain.substring(1) + predictedState;
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("IO Error occured");
		}
		
	}

	public String toString() {

		return markov.toStringTest();
	}
}
