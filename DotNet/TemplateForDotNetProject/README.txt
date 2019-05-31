**********************************KDLT*************************************

I plead with anybody consulting this Template to follow these instructions

1- Make sure you Enable-Migrations once your launch the solution on visual studio
(This is done from the PackageManager commandLine)

2-Make sure you go through each project under this solution as i took time to prepare the template
and comment some usefull information you might need for the exam

3-Files to be consulted:
	*ExamenFinal.Domain ==> Entities (Insert here your Entities)
	*ExamenFinal.Data ==> Configurations -->ExamenFinalConfiguration.cs(Consult class to insert config)
	*ExamenFinal.Data ==> CustomConventions -->ExamenFinalConvention.cs(Consult class to insert convention)
	*ExamnFinal.Data ==> Migrations( This folder contains your Migrations
				once you perform the migration commands on the package Manager view as follows:
			>Add-Migration
			>Update-Database
				      )
	*ExameFinal.Data --> ExamenFinalContext.cs(Consult class to view how data is sent to database and how your
				personalized configurations and conventions are taken into consideration)

	*ExamenFinal.Web --> Web.config( Consult file to see under the connectionString tag to see
		the name of the connectionString(ExamChain) and the name of the database(ExamDatabase)
					)

PS: I included a text file called dotNetRevNotes.txt with some quick prepared 
	code(looks like garbage but could be useful),u might want to check
	
									GOODLUCK
