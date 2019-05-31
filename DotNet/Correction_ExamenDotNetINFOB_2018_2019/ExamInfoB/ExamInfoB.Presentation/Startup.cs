using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(ExamInfoB.Presentation.Startup))]
namespace ExamInfoB.Presentation
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
