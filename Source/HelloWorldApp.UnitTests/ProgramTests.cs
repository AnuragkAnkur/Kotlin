using NUnit.Framework;

namespace HelloWorldApp.UnitTests
{
    [TestFixture]
    public class ProgramTests
    {
        [Test]
        public void CheckIfExists()
        {
            var type = typeof(Program);
            Assert.That(type, Is.Not.Null);
        }
    }
}
