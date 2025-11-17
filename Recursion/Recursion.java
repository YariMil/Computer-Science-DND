import java.util.ArrayList;

public class Recursion {

	// Prints the value of every node in the singly linked list with the given head,
	// but in reverse
	public static void printListInReverse(ListNode head) {
		if (head == null) {
			throw new IllegalArgumentException("List node can't be null");
		}
		if (head.getNext() == null) {
			System.out.println(head.getValue());
			return;
		}
		printListInReverse(head.getNext());
		System.out.println(head.getValue());
	}

	// For the given 2D array of Strings, replaces the String at index[r][c]
	// with "infected" unless the String is "vaccinated";
	// also, any Strings they are orthogonally adjacent to
	// that are not "vaccinated" will also be infected, and any adjacent to
	// them as well etc.
	// Infecting someone who is already infected has no effect
	// Trying to infect outside the confines of the grid also has no effect
	// Precondition: grid has no null entries
	public static void infect(String[][] grid, int r, int c) {
		if (grid == null) {
			throw new IllegalArgumentException("Grid can't be null");
		} else if (r < 0 || r >= grid.length) {
			throw new IllegalArgumentException("Row must be in bounds");
		} else if (c < 0 || c >= grid[r].length) {
			throw new IllegalArgumentException("Column must be in bounds");
		}
		if (!grid[r][c].equals("vaccinated") && !grid[r][c].equals("infected")) {
			grid[r][c] = "infected";
			for (int row = -1; row < 2; row += 2) {
				if (r + row >= 0 && r + row < grid.length) {
					infect(grid, r + row, c);
				}
			}
			for (int col = -1; col < 2; col += 2) {
				if (col + c >= 0 && c + col < grid[r].length) {
					infect(grid, r, col + c);

				}
			}
		}
		return;
	}

	// How many subsets are there of the numbers 1...n
	// that don't contain any consecutive integers?
	// e.g. for n = 4, the subsets are {}, {1}, {2}, {3}, {4},
	// {1, 3}, {1, 4}, {2, 4}
	// The other subsets of 1,2,3,4 that DO contain consecutive integers are
	// {1,2}, {2,3}, {3,4}, {1,2,3}, {1,2,4}, {1,3,4}, {1,2,3,4}
	// Precondition: n > 0
	public static long countNonConsecutiveSubsets(int n) {
		// Base case: length = 0, in which case return 1 ({})
		// For 1, additional is {1}. Total 2
		// For 2, additional is {2}. Total 3
		// For 3, additional is {1, 3} and {3}. Total 5
		// For 4 additional is {4}, {1, 4}, {2, 4}. Total 8
		// For 5 additional is {5}, {1, 3, 5}, {1, 5}, {2, 5}, {3, 5}. Total is 13
		// For 6, additional is {6}, {1, 6}, {2, 6}, {3,6}, {4,6} Total is 17
		if (n < 0) {
			throw new IllegalArgumentException("n must be greater than or equal to 0");
		}
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return 2;
		}
		long oneStageDown = countNonConsecutiveSubsets(n - 1);
		long twoStagesDown = countNonConsecutiveSubsets(n - 2);
		return oneStageDown + twoStagesDown;

	}

	// A kid at the bottom of the stairs can jump up 1, 2, or 3 stairs at a time.
	// How many different ways can they jump up n stairs?
	// Jumping 1-1-2 is considered different than jumping 1-2-1
	// Precondition: n > 0
	public static long countWaysToJumpUpStairs(int n) {
		// Base case, 1 stair return 1
		// 2 stairs, 1-1, 2 -> return 2
		// 3 stairs, 1-1-1, 1-2, 2-1, 3 --> 4
		// 4 stairs, 1-1-1-1, 1-1-2, 2-1-1, 1-2-1, 2-2, 1-3, 3-1 -> 7
		// 5 stairs, 1-1-1-1-1, 2-1-1-1, 1-2-1-1, 1-1-2-1, 1-1-1-2, 1-1-3, 1-3-1, 3-1-1, 2-3, 3-2 ->
		// 13
		// 6 stairs, 1-1-1-1-1-1, 2-1-1-1-1, 1-2-1-1-1, 1-1-2-1-1, 1-1-1-2-1, 1-1-1-1-2, 3-1-1-1,
		// 1-3-1-1, 1-1-3-1, 1-1-1-3, 1-2-3, 1-3-2, 2-1-3, 2-3-1, 3-2-1, 3-1-2, 24
		// 1, 2, 3, 4, 5, 6
		// 1, 2, 4, 7, 13, 24
		// 1, 2, 3, 6, 11
		// ITS JUST FIBONACCI AGAIN WHAT IS THIS
		if (n <= 0) {
			throw new IllegalArgumentException("Can't jump up 0 stairs buddy.");
		}
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		if (n == 3) {
			return 4;
		}
		return countWaysToJumpUpStairs(n - 1) + countWaysToJumpUpStairs(n - 2)
				+ countWaysToJumpUpStairs(n - 3);
	}

	// Everything above this line does NOT require a recursive helper method
	// ----------------------------------
	// Everything below this line requires a recursive helper method
	// Any recursive helper method you write MUST have a comment describing:
	// 1) what the helper method does/returns
	// 2) your description must include role of each parameter in the helper method

	// Prints all the subsets of str on separate lines
	// You may assume that str has no repeated characters
	// For example, subsets("abc") would print out "", "a", "b", "c", "ab", "ac",
	// "bc", "abc"
	// Order is your choice

	public static void printSubsets(String str) {
		if (str == null) {
			throw new IllegalArgumentException("String must be not null");
		}
		// Base case, empty string, just print that
		ArrayList<String> subsets = printSubsetsHelper(str, new ArrayList<String>());
		for (int i = 0; i < subsets.size(); i++) {
			System.out.println(subsets.get(i));
		}
	}

	public static ArrayList<String> printSubsetsHelper(String str, ArrayList<String> subsetList) {
		/*
		 * This helper method takes in a String representing the string printSubsets is perfomed on
		 * and an ArrayList of Strings repsenting the list of subsets we have already found. The
		 * method recursively calls itself using the subsetList past in and the String passed in
		 * without the last character. This continues until the string is reduced to an empty
		 * string, at which point it is added to the ArrayList. From there, the method adds the last
		 * letter of the string that was passed into the previous recursion iteration to every
		 * element in subsetList (the new subsets are added as elements separate from the previously
		 * found subsets). The method returns an ArrayList of Strings representing all the subsets
		 * currently found.
		 */

		if (str.length() <= 0) {
			subsetList.add("");
			return subsetList;
		}
		subsetList = printSubsetsHelper(str.substring(0, str.length() - 1), subsetList);
		int initialSubsetsize = subsetList.size();
		for (int i = 0; i < initialSubsetsize; i++) {
			String currentChar = str.charAt(str.length() - 1) + "";
			subsetList.add(subsetList.get(i) + currentChar);
		}
		return subsetList;
	}

	// List contains a single String to start.
	// Prints all the permutations of str on separate lines
	// You may assume that str has no repeated characters
	// For example, permute("abc") could print out "abc", "acb", "bac", "bca",
	// "cab", "cba"
	// Order is your choice

	public static void printPermutations(String str) {
		if (str == null) {
			throw new IllegalArgumentException("Thought you were slick huh. String can't be null.");
		} else if (str.isEmpty()) {
			System.out.println("");
			return;
		}
		ArrayList<String> permutations = permutationsHelper(str.substring(1), str.charAt(0));
		for (int i = 0; i < permutations.size(); i++) {
			System.out.println(permutations.get(i));
		}
	}

	/*
	 * This helper method takes in a string representing the string permutations are to be performed
	 * on and a character representing a character that will be added to elements of an ArrayList.
	 * The method returns an ArrayList of Strings. The method recursively calls itself with one
	 * character cut off from the end (the cut character is passed in as the addChar variable) until
	 * the str parameter is an empty string. At that point, an ArrayList is created with a string
	 * that solely contains the addChar variable. From there, the method receives an ArrayList of
	 * incomplete permutations of the str parameter gotten from the recursive returns. Then the
	 * method creates a new ArrayList of Strings and adds to it all the elements from the
	 * permutations ArrayList with the addChar parameter added at each index.
	 */
	public static ArrayList<String> permutationsHelper(String str, char addChar) {
		if (str.length() == 0) {
			ArrayList<String> newPermutations = new ArrayList<String>();
			newPermutations.add("" + addChar);
			return newPermutations;
		}
		ArrayList<String> permutations = permutationsHelper(str.substring(1), str.charAt(0));
		ArrayList<String> newPermutations = new ArrayList<String>();
		for (String permutation : permutations) {
			for (int i = 0; i < permutation.length() + 1; i++) {
				// Adding the character in between all the letters individually
				String s = permutation.substring(0, i) + addChar + permutation.substring(i);
				newPermutations.add(s);
			}
		}
		return newPermutations;
	}

	/*
	 * This helper method takes in an array of ints. The method splits the given array down the
	 * middle into two, then calls itself on the split arrays, which splits them into two. That
	 * happens until the array passed in only has one element, at which point it is returned (since
	 * it's sorted by default). Once the method has received two sorted arrays from its two
	 * recursive calls and combines them together by comparing values in the arrays and adding them
	 * one by one into one big array. What emerges at the end is one sorted array that is hen
	 * returned by the method.
	 */
	public static int[] mergeHelper(int[] ints) {
		if (ints.length == 1) {
			return ints;
		}
		int mid = ints.length / 2;
		int[] leftHalf = new int[mid];
		int[] rightHalf = new int[ints.length - mid];
		int leftSplit = 0;
		int rightSplit = 0;
		for (int i = 0; i < ints.length; i++) {
			if (i < mid) {
				leftHalf[leftSplit] = ints[i];
				leftSplit++;
			} else {
				rightHalf[rightSplit] = ints[i];
				rightSplit++;
			}
		}
		leftHalf = mergeHelper(leftHalf);
		rightHalf = mergeHelper(rightHalf);
		int[] sorted = new int[ints.length];
		int mergeIndex = 0;
		int rightMerge = 0;
		int leftMerge = 0;
		while (leftMerge < leftHalf.length || rightMerge < rightHalf.length) {
			if (leftMerge >= leftHalf.length) {
				sorted[mergeIndex] = rightHalf[rightMerge];
				rightMerge++;
			} else if (rightMerge >= rightHalf.length) {
				sorted[mergeIndex] = leftHalf[leftMerge];
				leftMerge++;
			} else if (leftHalf[leftMerge] < rightHalf[rightMerge]) {
				sorted[mergeIndex] = leftHalf[leftMerge];
				leftMerge++;
			} else {
				sorted[mergeIndex] = rightHalf[rightMerge];
				rightMerge++;
			}
			mergeIndex++;
		}
		return sorted;
	}

	// Performs a mergeSort on the given array of ints
	// Precondition: you may assume there are NO duplicates!!!
	public static void mergeSort(int[] ints) {
		if (ints == null) {
			throw new IllegalArgumentException("Array can't be null");
		}
		int[] sorted = mergeHelper(ints);
		for (int i = 0; i < sorted.length; i++) {
			ints[i] = sorted[i];
		}
	}

	// Performs a quickSort on the given array of ints
	// Use the middle element (index n/2) as the pivot
	// Precondition: you may assume there are NO duplicates!!!
	public static void quickSort(int[] ints) {
		if (ints == null) {
			throw new IllegalArgumentException("Array can't be null");
		}
		int[] sorted = quickSortHelper(ints);
		for (int i = 0; i < sorted.length; i++) {
			ints[i] = sorted[i];
		}

	}

	/*
	 * This method takes in an array of ints representing the array to be sorted. The method
	 * designates a pivot element (index at n/2) and then separates the array into two arrays: One
	 * comprised of elements larger than the pivot, and one containing elements smaller than the
	 * pivot. The array then recursively calls itself on the two sub arrays. This continues until an
	 * array that is passed in has 1 or no elements. At that point, the method returns the array it
	 * has been passed in. Once the method has been returned two sorted arrays from its recrusive
	 * calls, it then splices the two arrays into one big array. That array is then returned.
	 */
	public static int[] quickSortHelper(int[] ints) {
		if (ints.length <= 1) {
			return ints;
		}
		int pivotIndex = (ints.length / 2);
		int numHigher = 0;
		int numSmaller = 0;
		int smallerIndex = 0;
		int higherIndex = 0;
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] > ints[pivotIndex]) {
				numHigher++;
			} else if (ints[i] < ints[pivotIndex]) {
				numSmaller++;
			}
		}
		int[] lowerPivot = new int[numSmaller];
		int[] higherPivot = new int[numHigher];
		for (int i = 0; i < ints.length; i++) {
			if (i != pivotIndex) {


				if (smallerIndex >= lowerPivot.length) {
					higherPivot[higherIndex] = ints[i];
					higherIndex++;
				} else if (higherIndex >= higherPivot.length) {
					lowerPivot[smallerIndex] = ints[i];
					smallerIndex++;
				} else if (ints[i] < ints[pivotIndex]) {
					lowerPivot[smallerIndex] = ints[i];
					smallerIndex++;
				} else {
					higherPivot[higherIndex] = ints[i];
					higherIndex++;
				}
			}
		}
		lowerPivot = quickSortHelper(lowerPivot);
		higherPivot = quickSortHelper(higherPivot);
		int[] sorted = new int[ints.length];
		int sortIndex = 0;
		for (int i = 0; i < lowerPivot.length; i++) {
			sorted[sortIndex] = lowerPivot[i];
			sortIndex++;
		}
		sorted[sortIndex] = ints[pivotIndex];
		sortIndex++;
		for (int i = 0; i < higherPivot.length; i++) {
			sorted[sortIndex] = higherPivot[i];
			sortIndex++;
		}
		return sorted;
	}

	// Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem:
	// disks start on tower 0 and must end on tower 2.
	// The towers are number 0, 1, 2, and each move should be of
	// the form "1 -> 2", meaning "take the top disk of tower 1 and
	// put it on tower 2" etc.
	public static void solveHanoi(int startingDisks) {
		if (startingDisks <= 0) {
			throw new IllegalArgumentException("Disc number must be greater than 0.");
		}
		solveHanoiHelper(startingDisks, 0, 2);
	}

	/*
	 * This method takes in an int representing the starting disk amount, an int representing the
	 * tower from which the discs need to be moved from (currentTower), and an int representing the
	 * tower to which the discs need to be moved (targetTower). The method figures out which is the
	 * tower remaining of the 3, then moves discs-1 discs to the nonTargetTower. It then moves a
	 * disc from the currentTower to the targetTower, then moves the discs-1 discs it moved to the
	 * nonTargetTower to the targetTower. If the discs int passed in is 1 (as is bound to happen due
	 * to recursive calls), the method just moves the disc from the currentTower to the targetTower
	 * and returns nothing. The method itself returns nothing.
	 */
	public static void solveHanoiHelper(int discs, int currentTower, int targetTower) {
		if (discs == 1) {
			System.out.println(currentTower + " -> " + targetTower);
			return;
		}
		int[] towerOptions = new int[] {0, 1, 2};
		// Determine the nonTargetTower
		int nonTargetTower = 0;
		for (int i = 0; i < 3; i++) {
			if (towerOptions[i] != currentTower && towerOptions[i] != targetTower) {
				nonTargetTower = towerOptions[i];
			}
		}
		solveHanoiHelper(discs - 1, currentTower, nonTargetTower);
		System.out.println(currentTower + " -> " + targetTower);
		solveHanoiHelper(discs - 1, nonTargetTower, targetTower);

	}

	// You are partaking in a scavenger hunt!
	// You've gotten a secret map to find many of the more difficult
	// items, but they are only available at VERY specific times at
	// specific places. You have an array, times[], that lists at which
	// MINUTE an item is available. Times is sorted in ascending order.
	// Items in the ScavHunt are worth varying numbers of points.
	// You also have an array, points[], same length as times[],
	// that lists how many points each of the corresponding items is worth.
	// Problem is: to get from one location to the other takes 5 minutes,
	// so if there is an item, for example, available at time 23 and another
	// at time 27, it's just not possible for you to make it to both: you'll
	// have to choose!
	// (but you COULD make it from a place at time 23 to another at time 28)
	// Write a method that returns the maximum POINTS you can get.
	// For example, if times = [3, 7, 9]
	// and points = [10, 15, 10]
	// Then the best possible result is getting the item at time 3 and the one at
	// time 9
	// for a total of 20 points, so it would return 20.
	public static int scavHunt(int[] times, int[] points) {
		if (times == null || points == null) {
			throw new IllegalArgumentException("Times and points can't be negative");
		} else if (times.length == 0 || points.length == 0) {
			throw new IllegalArgumentException("Times and points must be nonempty");
		} else if (times.length != points.length) {
			throw new IllegalArgumentException("Times and points have to be the same length!");
		}
		return scavHuntHelper(times, points, 0);
	}

	/*
	 * This method takes in an two int arrays representing times and points respectively, as well as
	 * the current time the scavenger hunt is on right now. The method figures out the reward that
	 * would be available five minutes after currentTime. The method then calls itself to figure out
	 * the maximum points that could be obtained if the reward available at the current time is
	 * picked, and also the maximum points if it isn't picked. If there are no rewards five minutes
	 * after or if there are no more rewards at all, the method doesn't call itself and assigns the
	 * pointsWithReward and pointsWithoutReward variables to the points at the current time and 0,
	 * respectively. The method compares pointsWithReward and pointsWithoutReward and returns the
	 * greater value.
	 */
	public static int scavHuntHelper(int[] times, int[] points, int currentTime) {
		int fiveMinutesAfter = -1;
		for (int i = currentTime; i < times.length; i++) {
			if (times[i] >= (times[currentTime] + 5)) {
				fiveMinutesAfter = i;
				break;
			}
		}
		int pointsWithReward = 0;
		int pointsWithoutReward = 0;
		// Option 1: Choose the reward at current time
		// Edge case, there are no more rewards 5 minutes after this
		if (fiveMinutesAfter != -1) {
			pointsWithReward =
					points[currentTime] + scavHuntHelper(times, points, fiveMinutesAfter);
		} else {
			// This is the base case
			pointsWithReward = points[currentTime];
		}
		// Option 2: Don't choose the reward, advance to the next reward
		// Base case: No more points without reward
		if (currentTime != times.length - 1) {
			pointsWithoutReward = scavHuntHelper(times, points, currentTime + 1);
		}
		// Do nothing if base case it's 0 anyway.

		if (pointsWithReward > pointsWithoutReward) {
			return pointsWithReward;
		} else {
			return pointsWithoutReward;
		}
	}

}
