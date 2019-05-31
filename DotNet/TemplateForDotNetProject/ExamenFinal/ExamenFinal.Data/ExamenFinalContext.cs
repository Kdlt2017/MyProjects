using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamenFinal.Data
{
    public class ExamenFinalContext:DbContext
    {
        public ExamenFinalContext():base("name=ExamChain")
        {

        }

        //Insert the properties DbSets here as follows: public DbSet<User> Users { get; set; }
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            //Insert Here your personal configuration and convention implemented in the Configurations and CustomConventions folders respectively
            //Example for ExamenFinalConfiguration class: modelBuilder.Configurations.Add(new ExamenFinalConfiguration());
            //Same for Conventions for the ExamenFinalConvention class: modelBuilder.Conventions.Add(new ExamenFinalConvention());


        }

    }
}
