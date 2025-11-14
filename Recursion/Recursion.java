import java.util.ArrayList;

public class Recursion {

	// Prints the value of every node in the singly linked list with the given head,
	// but in reverse
	public static void printListInReverse(ListNode head) {
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
		// Base case, empty string, just print that
		// For each string, have a subset length. Print out the subsets of that length
		// go down
		ArrayList<String> subsets = printSubsetsHelper(str, new ArrayList<String>());
		for (int i = 0; i < subsets.size(); i++) {
			System.out.println(subsets.get(i));
		}
	}

	public static ArrayList<String> printSubsetsHelper(String str, ArrayList<String> subsetList) {
		/*
		 * This helper method takes in a String which is the string passed into printSubsets and an
		 * integer representing length The helper method prints out all the subsets of the string
		 * that have the length of the length passed in Basically, it starts at the first character
		 * and prints out a substring of length length. If it can, it moves to the next character
		 * and tries to print out a substring of length length starting from that character now If
		 * that substring does not exist, it calls itself recursively with length - 1 instead of
		 * length. This shortens the length of the substrings more and more until length is 0, in
		 * which case an empty string is printed and the method ends.
		 */
		// if (length == 0) {
		// System.out.println("");
		// return subsetList;
		// }
		// for (int i = 0; i + length < str.length() + 1; i++) {
		// System.out.println(str.substring(i, i + length));
		// }
		// printSubsetsHelper(str, length - 1, subsetList);
		// return subsetList;
		// Second way of doing it
		if (str.equals("")) {
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
				String s = permutation.substring(0, i) + addChar + permutation.substring(i);
				newPermutations.add(s);
			}
		}
		return newPermutations;
	}

	public static void printPermutations(String str) {
		ArrayList<String> permutations = permutationsHelper(str.substring(1), str.charAt(0));
		for (int i = 0; i < permutations.size(); i++) {
			System.out.println(permutations.get(i));
		}
	}

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
		int[] sorted = mergeHelper(ints);
		for (int i = 0; i < sorted.length; i++) {
			ints[i] = sorted[i];
		}
	}

	// Performs a quickSort on the given array of ints
	// Use the middle element (index n/2) as the pivot
	// Precondition: you may assume there are NO duplicates!!!
	public static void quickSort(int[] ints) {
		int[] sorted = quickSortHelper(ints);
		for (int i = 0; i < sorted.length; i++) {
			ints[i] = sorted[i];
		}

	}

	public static void changeAThing(int[] ints) {
		ints[1] = 10000000;
	}

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
		solveHanoiHelper(startingDisks, 0, 2);
	}

	/*
	 * This method takes in an int representing the starting disk amount, an int representing the
	 * tower from which the discs need to be moved from (currentTower), an int representing the
	 * tower to which the discs need to be moved (targetTower).
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
		return scavHuntHelper(times, points, 0);
	}

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
