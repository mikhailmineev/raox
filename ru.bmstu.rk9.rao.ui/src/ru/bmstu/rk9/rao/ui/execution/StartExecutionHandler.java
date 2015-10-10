package ru.bmstu.rk9.rao.ui.execution;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.builder.EclipseOutputConfigurationProvider;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.ui.validation.DefaultResourceUIValidatorExtension;

import ru.bmstu.rk9.rao.IMultipleResourceGenerator;
import ru.bmstu.rk9.rao.ui.simulation.ModelExecutionSourceProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class StartExecutionHandler extends AbstractHandler {
	@Inject
	private IMultipleResourceGenerator generator;

	@Inject
	private Provider<EclipseResourceFileSystemAccess2> fileAccessProvider;

	@Inject
	private IResourceSetProvider resourceSetProvider;

	@Inject
	private EclipseOutputConfigurationProvider outputConfigurationProvider;

	@Inject
	DefaultResourceUIValidatorExtension validatorExtension;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow activeWorkbenchWindow = HandlerUtil
				.getActiveWorkbenchWindow(event);
		ModelExecutionSourceProvider.setRunningState(activeWorkbenchWindow,
				true);
		ExecutionManager executionManager = new ExecutionManager(event,
				fileAccessProvider.get(), resourceSetProvider,
				outputConfigurationProvider, generator, validatorExtension);
		executionManager.execute(false);

		return null;
	}
}
