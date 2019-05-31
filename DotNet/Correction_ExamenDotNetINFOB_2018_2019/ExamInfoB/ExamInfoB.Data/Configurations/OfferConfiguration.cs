using ExamInfoB.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamInfoB.Data.Configurations
{
    public class OfferConfiguration:EntityTypeConfiguration<Offre>
    {
        public OfferConfiguration()
        {
            HasMany(o => o.Postulants).WithMany(p => p.Offres)
                .Map(m =>
                {

                    m.ToTable("Candidature");
                    m.MapLeftKey("Postulant");
                    m.MapRightKey("Offre");

                });
        }
    }
}
