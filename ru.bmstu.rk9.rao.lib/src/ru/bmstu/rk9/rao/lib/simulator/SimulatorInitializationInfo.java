package ru.bmstu.rk9.rao.lib.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import ru.bmstu.rk9.rao.lib.dpt.AbstractDecisionPoint;
import ru.bmstu.rk9.rao.lib.process.Block;
import ru.bmstu.rk9.rao.lib.result.AbstractResult;

public class SimulatorInitializationInfo {
	private static final Runnable DEFAULT_EXPERIMENTS = new DefaultExperiments();
	public static final Function<Double, String> DEFAULT_TIME_FORMATTER = (currentTime) -> currentTime.toString();

	private final List<Runnable> initList = new ArrayList<>();
	private final List<Supplier<Boolean>> terminateConditions = new ArrayList<>();
	private final List<AbstractDecisionPoint> decisionPoints = new ArrayList<>();
	private final List<Block> processBlocks = new ArrayList<>();
	private final List<AbstractResult<?>> results = new ArrayList<>();

	public void setTimeStart(Supplier<Double> timeStart) {
		this.timeStart = timeStart.get();
	}

	public List<Runnable> getInitList() {
		return initList;
	}

	public List<Supplier<Boolean>> getTerminateConditions() {
		return terminateConditions;
	}

	public List<AbstractDecisionPoint> getDecisionPoints() {
		return decisionPoints;
	}

	public List<Block> getProcessBlocks() {
		return processBlocks;
	}

	public List<AbstractResult<?>> getResults() {
		return results;
	}

	private Function<Double, String> timeFormatter = DEFAULT_TIME_FORMATTER;

	public Function<Double, String> getTimeFormatter() {
		return timeFormatter;
	}

	public void setTimeFormatter(Function<Double, String> timeFormatter) {
		this.timeFormatter = timeFormatter;
	}

	private double timeStart = 0;

	public double getTimeStart() {
		return timeStart;
	}

	private Runnable experiments;

	public void setExperiments(Runnable experiments) {
		this.experiments = experiments;
	}

	public Runnable getExperiments() {
		if (experiments == null)
			return DEFAULT_EXPERIMENTS;
		return experiments;
	}
}
