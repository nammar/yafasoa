//  SinglePointCrossover.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package jmetal.operators.crossover;

import jmetal.core.SmartCitySolution;
import jmetal.encodings.solutionType.SOA_SOLUTION_TYPE;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import jmetal.util.PseudoRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import Helper.Gene;

/**
 * This class allows to apply a Single Point crossover operator using two parent
 * solutions.
 */
public class SmartCitySOACrossover extends Crossover {
	/**
	 * Valid solution types to apply this operator
	 */
	protected static Random random = new Random();
	private static final List VALID_TYPES = Arrays.asList(SOA_SOLUTION_TYPE.class, SOA_SOLUTION_TYPE.class,
			SOA_SOLUTION_TYPE.class);

	private Double crossoverProbability_ = null;
	private int corssoverPoint_ = 0;
	private int Solutionlength_ = 0;
	
	/**
	 * Genes to keep untouched
	 */
	private static final int OFFSET = 1;

	/**
	 * Constructor Creates a new instance of the single point crossover operator
	 */
	public SmartCitySOACrossover(HashMap<String, Object> parameters) {
		super(parameters);
		if (parameters.get("probability") != null)
			crossoverProbability_ = (Double) parameters.get("probability");

		crossoverProbability_ = (Double) parameters.get("probability");
		System.out.println("cross over Prob    " + crossoverProbability_);
		// corssoverPoint_= parameters.get("corssoverPoint") ;

		Solutionlength_ = (Integer) parameters.get("Solutionlength");
		// System.out.println("cross over length "+Solutionlength_);
	}

	/**
	 * Perform the crossover operation.
	 * 
	 * @param probability
	 *            Crossover probability
	 * @param parent1
	 *            The first parent
	 * @param parent2
	 *            The second parent
	 * @return An array containing the two offsprings
	 * @throws JMException
	 */
	public SmartCitySolution[] doCrossover(double probability,

			SmartCitySolution parent1, SmartCitySolution parent2) throws JMException {
		System.out.println("parent1  size" + parent1.getGenome().size() + " parent2  size"
				+ parent2.getGenome().size());

		System.out.println("parent1.getGenes().size()  " + parent1.getGenome().size());
		
		/* Size must be greater than OFFSET */
		corssoverPoint_ = random.nextInt(parent1.getGenome().size() - OFFSET);
		corssoverPoint_ += OFFSET;
		
		System.out.println("corssoverPoint_  **************   " + corssoverPoint_);
		SmartCitySolution[] offSpring = new SmartCitySolution[2];
		offSpring[0] = new SmartCitySolution(parent1);
		offSpring[1] = new SmartCitySolution(parent2);
		
		/*
		 * New Array list of driver preferences, Parking requirements and spots.
		 */
		ArrayList<Gene> genomeOne = new ArrayList<>(); 
		ArrayList<Gene> genomeTwo = new ArrayList<>();
		
		for (int i = 0; i < OFFSET; i++) {
			genomeOne.set(i, parent1.getGenome().get(i));
			genomeTwo.set(i, parent2.getGenome().get(i));
		}
		
		try {
			if (PseudoRandom.randDouble() < probability) {
				genomeOne = new ArrayList<>();
				for (int part1 = OFFSET; part1 < corssoverPoint_; part1++) {
					genomeOne.add(parent1.getGenome().get(part1));
				}

				for (int part2 = corssoverPoint_; part2 < Solutionlength_; part2++) {
					genomeOne.add(parent2.getGenome().get(part2));
				}

				for (int part1 = OFFSET; part1 < corssoverPoint_; part1++) {
					genomeTwo.add(parent1.getGenome().get(part1));

				}

				for (int part2 = corssoverPoint_; part2 < Solutionlength_; part2++)
				{
					genomeTwo.add(parent2.getGenome().get(part2));

				}

				System.out.println("Genome one size : " + genomeOne.size());

				System.out.println("Genome Two size : " + genomeTwo.size());

				offSpring[0].setGenome(genomeOne);
				offSpring[1].setGenome(genomeTwo);

			}

		} catch (ClassCastException e1) {
			Configuration.logger_.severe("SinglePointCrossover.doCrossover: Cannot perfom " + "SinglePointCrossover");
			Class cls = java.lang.String.class;
			String name = cls.getName();
			throw new JMException("Exception in " + name + ".doCrossover()");
		}

		return offSpring;
	} 

	/**
	 * Executes the operation
	 * 
	 * @param object
	 *            An object containing an array of two solutions
	 * @return An object containing an array with the offSprings
	 * @throws JMException
	 */
	public Object execute(Object object) throws JMException {
		SmartCitySolution[] parents = (SmartCitySolution[]) object;

		if (!(VALID_TYPES.contains(parents[0].getType().getClass())
				&& VALID_TYPES.contains(parents[1].getType().getClass()))) {

			Configuration.logger_.severe("SinglePointCrossover.execute: the solutions "
					+ "are not of the right type. The type should be 'Binary' or 'Int', but " + parents[0].getType()
					+ " and " + parents[1].getType() + " are obtained");

			Class cls = java.lang.String.class;
			String name = cls.getName();
			throw new JMException("Exception in " + name + ".execute()");
		} // if

		if (parents.length < 2) {
			Configuration.logger_.severe("SinglePointCrossover.execute: operator " + "needs two parents");
			Class cls = java.lang.String.class;
			String name = cls.getName();
			throw new JMException("Exception in " + name + ".execute()");
		}

		SmartCitySolution[] offSpring;
		offSpring = doCrossover(crossoverProbability_, parents[0], parents[1]);

		// -> Update the offSpring solutions
		for (int i = 0; i < offSpring.length; i++) {
			offSpring[i].setCrowdingDistance(0.0);
			offSpring[i].setRank(0);
		}
		return offSpring;
	} // execute
} // SinglePointCrossover
