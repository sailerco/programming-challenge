/**
 * The stats package for the data challenge containing the stats super class
 * {@link de.bcxp.challenge.stats.Stats}, the subclasses {@link de.bcxp.challenge.stats.CountryStats},
 * {@link de.bcxp.challenge.stats.WeatherStats}. The Stats class provides methods to get the content of a file
 * through a given {@link de.bcxp.challenge.dataReader.DataFileReader}. It also provides utilities for parsing numbers
 * in strings to integers while checking the local number format.
 * The subclasses provide methods to analyse data specific stats.
 */
package de.bcxp.challenge.stats;