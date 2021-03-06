package org.ansj.training.bigram;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import love.cq.util.IOUtil;
import love.cq.util.StringUtil;

import org.ansj.domain.BigramEntry;
import org.ansj.domain.Term;
import org.ansj.domain.TermNature;
import org.ansj.domain.TermNatures;
import org.ansj.library.InitDictionary;
import org.ansj.library.TwoWordLibrary;

/**
 * 用于生成bigramdicmake的字典
 * 
 * @author ansj
 * 
 */
public class BigramdictMake {
	// public static void main(String[] args) throws IOException {
	// BufferedReader reader =
	// IOUtil.getReader("data/bigramdict/bigramdict.dic", "UTF-8");
	// String temp = null;
	// String[] strs = null;
	// BigramTable[] result = new BigramTable[0];
	// int fromId = 0;
	// int toId = 0;
	// int freq = 0;
	// int length = 0;
	// BigramTable from = null;
	// BigramEntry to = null;
	// while ((temp = reader.readLine()) != null) {
	// if (StringUtil.isBlank(temp)) {
	// continue;
	// }
	//
	// strs = temp.split("\t");
	// freq = Integer.parseInt(strs[1]);
	// strs = strs[0].split("@");
	// if ((fromId = InitDictionary.getWordId(strs[0])) <= 0) {
	// fromId = 0;
	// }
	// if ((toId = InitDictionary.getWordId(strs[1])) <= 0) {
	// toId = -1;
	// }
	//
	// from = new BigramTable(fromId);
	// to = new BigramEntry(toId, freq);
	//
	// int index = Arrays.binarySearch(result, from);
	// if (index > -1) {
	// from = result[index];
	// } else {
	// result = Arrays.copyOf(result, result.length + 1);
	// result[result.length - 1] = from;
	// Arrays.sort(result);
	// }
	//
	// BigramEntry[] bes = from.bes;
	// if (bes == null) {
	// bes = new BigramEntry[0];
	// }
	// length = bes.length;
	// if (Arrays.binarySearch(bes, to) < 0) {
	// // 数组扩容
	// from.bes = Arrays.copyOf(bes, length + 1);
	// bes = from.bes;
	// bes[length] = to;
	// Arrays.sort(bes);
	// }
	// }
	//
	// TwoWordLibrary.setBigramTables(result);
	// Term _from = new Term("中国", 0, new TermNatures(TermNature.NULL));
	// _from.getTermNatures().id = 101617;
	// Term _to = new Term("家庭", 0, new TermNatures(TermNature.NULL));
	// _to.getTermNatures().id = 136991;
	// System.out.println(TwoWordLibrary.getTwoWordFreq(_from, _to));
	//
	// IOUtil.WriterObj("data/bigramdict/bigramdict.data", result);
	// }

	public static void main(String[] args) throws IOException {
		BufferedReader reader = IOUtil.getReader("data/bigramdict/bigramdict.dic", "UTF-8");
		String temp = null;
		String[] strs = null;
		BigramEntry[][] result = new BigramEntry[423152][0];
		int fromId = 0;
		int toId = 0;
		int freq = 0;
		int length = 0;
		BigramEntry to = null;
		while ((temp = reader.readLine()) != null) {
			if (StringUtil.isBlank(temp)) {
				continue;
			}

			strs = temp.split("\t");
			freq = Integer.parseInt(strs[1]);
			strs = strs[0].split("@");
			if ((fromId = InitDictionary.getWordId(strs[0])) <= 0) {
				fromId = 0;
			}
			if ((toId = InitDictionary.getWordId(strs[1])) <= 0) {
				toId = -1;
			}

			to = new BigramEntry(toId, freq);

			int index = Arrays.binarySearch(result[fromId], to);
			if (index > -1) {
				continue;
			} else {
				length = result[fromId].length ; 
				result[fromId] = Arrays.copyOf(result[fromId], length + 1);
				result[fromId][length] = to;
				Arrays.sort(result[fromId]);
			}

		}

		TwoWordLibrary.setBigramTables(result);
		Term _from = new Term("中国", 0, new TermNatures(TermNature.NULL));
		_from.getTermNatures().id = 101617;
		Term _to = new Term("家庭", 0, new TermNatures(TermNature.NULL));
		_to.getTermNatures().id = 136991;
		System.out.println(TwoWordLibrary.getTwoWordFreq(_from, _to));

		IOUtil.WriterObj("data/bigramdict/bigramdict.data", result);
	}
}
