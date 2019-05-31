using ExamInfoB.Data.Configurations;
using ExamInfoB.Data.CustomConventions;
using ExamInfoB.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamInfoB.Data
{
    public class ExamInfoBContext:DbContext
    {
        public ExamInfoBContext():base("name=MyChain")
        {

        }
        public DbSet<Postulant> Postulants { get; set; }
        public DbSet<Offre> Offres { get; set; }
        public DbSet<Entreprise> Entreprises { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new OfferConfiguration());
            modelBuilder.Conventions.Add(new KeyConvention());
        }

    }
}
