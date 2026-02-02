import java.io.*;
import java.util.*;

// You are allowed (and expected!) to use either Java's ArrayDeque or LinkedList class to make
// stacks and queues


public class CookieMonster {

	private int[][] cookieGrid;
	private int numRows;
	private int numCols;

	// Constructs a CookieMonster from a file with format:
	// numRows numCols
	// <<rest of the grid, with spaces in between the numbers>>
	public CookieMonster(String fileName) {
		int row = 0;
		int col = 0;
		try {
			Scanner input = new Scanner(new File(fileName));

			numRows = input.nextInt();
			numCols = input.nextInt();
			cookieGrid = new int[numRows][numCols];

			for (row = 0; row < numRows; row++)
				for (col = 0; col < numCols; col++)
					cookieGrid[row][col] = input.nextInt();

			input.close();
		} catch (Exception e) {
			System.out.print("Error creating maze: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}

	}

	public CookieMonster(int[][] cookieGrid) {
		this.cookieGrid = cookieGrid;
		this.numRows = cookieGrid.length;
		this.numCols = cookieGrid[0].length;
	}

	// You may find it VERY helpful to write this helper method. Or not!
	private boolean validPoint(int row, int col) {
		if (row >= cookieGrid.length || row < 0) {
			return false;
		} else if (col >= cookieGrid[row].length || col < 0) {
			return false;
		}
		return true;
	}

	/*
	 * RECURSIVELY calculates the route which grants the most cookies. Returns the maximum number of
	 * cookies attainable.
	 */
	public int recursiveCookies() {
		return recursiveCookies(0, 0);
	}

	// Returns the maximum number of cookies edible starting from (and including)
	// cookieGrid[row][col]
	public int recursiveCookies(int row, int col) {
		int cookiesHere = cookieGrid[row][col];
		int cookiesDown = 0;
		int cookiesLeft = 0;
		if (validPoint(row + 1, col)) {
			if (cookieGrid[row + 1][col] != -1) {
				cookiesDown = recursiveCookies(row + 1, col);
			}
		}
		if (validPoint(row, col + 1)) {
			if (cookieGrid[row][col + 1] != -1) {
				cookiesLeft = recursiveCookies(row, col + 1);
			}

		}
		return cookiesHere + Math.max(cookiesDown, cookiesLeft);
	}

	/*
	 * Calculate which route grants the most cookies using a QUEUE. Returns the maximum number of
	 * cookies attainable.
	 */
	/* From any given position, always add the path right before adding the path down */
	public int queueCookies() {
		/*
		 * Plan of action: Start with 1 guy Send out two guys right and down Check if any of them
		 * died, if yes that guy doesn't get added Pop off the guy we just processed, add whichever
		 * ones didn't die to the queue. We done now. End when everyone in the queue is on the
		 * ending square
		 */
		// This only works for basic arrays. Need to fix this with dynamic max changing and also
		// maybe end when the queue has nothing?
		ArrayDeque<OrphanScout> q = new ArrayDeque<OrphanScout>();
		q.add(new OrphanScout(0, 0, cookieGrid[0][0]));
		int max = 0;
		while (!q.isEmpty()) {
			int processing = q.size();
			for (int i = 0; i < processing; i++) {
				OrphanScout removedOrphan = q.poll();
				int currRow = removedOrphan.getEndingRow();
				int currCol = removedOrphan.getEndingCol();
				int currCookies = removedOrphan.getCookiesDiscovered();
				if (validPoint(currRow, currCol + 1)) {
					OrphanScout orphanRight = new OrphanScout(currRow, currCol + 1,
							currCookies + cookieGrid[currRow][currCol + 1]);
					if (!(orphanRight.getCookiesDiscovered() < currCookies)) {
						// Orphan isn't dead yet! WOOOO
						if (max < orphanRight.getCookiesDiscovered()) {
							max = orphanRight.getCookiesDiscovered();
						}
						q.add(orphanRight);
					}
				}
				if (validPoint(currRow + 1, currCol)) {
					OrphanScout orphanDown = new OrphanScout(currRow + 1, currCol,
							currCookies + cookieGrid[currRow + 1][currCol]);
					if (!(orphanDown.getCookiesDiscovered() < currCookies)) {
						// Orphan isn't dead! WOOOO
						q.add(orphanDown);
						if (max < orphanDown.getCookiesDiscovered()) {
							max = orphanDown.getCookiesDiscovered();
						}
					}

				}

			}
		}
		return max;
	}


	/*
	 * Calculate which route grants the most cookies using a stack. Returns the maximum number of
	 * cookies attainable.
	 */
	/* From any given position, always add the path right before adding the path down */
	public int stackCookies() {
		Stack<OrphanScout> bigStack = new Stack<OrphanScout>();
		bigStack.add(new OrphanScout(0, 0, cookieGrid[0][0]));
		int max = 0;
		while (!bigStack.isEmpty()) {
			int processing = bigStack.size();
			for (int i = 0; i < processing; i++) {
				OrphanScout endingOrphan = bigStack.pop();
				int currRow = endingOrphan.getEndingRow();
				int currCol = endingOrphan.getEndingCol();
				int currCookies = endingOrphan.getCookiesDiscovered();
				if (validPoint(endingOrphan.getEndingRow(), endingOrphan.getEndingCol() + 1)) {
					OrphanScout orphanRight = new OrphanScout(currRow, currCol + 1,
							currCookies + cookieGrid[currRow][currCol + 1]);
					if (orphanRight.getCookiesDiscovered() >= currCookies) {
						bigStack.push(orphanRight);
						if (max < orphanRight.getCookiesDiscovered()) {
							max = orphanRight.getCookiesDiscovered();
						}
					}

				}
				if (validPoint(endingOrphan.getEndingRow() + 1, endingOrphan.getEndingCol())) {
					OrphanScout orphanDown = new OrphanScout(currRow + 1, currCol,
							currCookies + cookieGrid[currRow + 1][currCol]);
					if (orphanDown.getCookiesDiscovered() >= currCookies) {
						bigStack.push(orphanDown);
						if (max < orphanDown.getCookiesDiscovered()) {
							max = orphanDown.getCookiesDiscovered();
						}
					}
				}
			}
		}
		return max;
	}

}
