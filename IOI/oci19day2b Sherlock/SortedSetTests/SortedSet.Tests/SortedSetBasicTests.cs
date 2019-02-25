using NUnit.Framework;

using System.Collections.Generic;

namespace Tests {

    public class SortedSetBasicTests {

        [Test]
        public void Test1() {
            SortedSet<int> s = new SortedSet<int>();
            s.Add(7);
            s.Add(9);
            s.Add(3);
            s.Add(5);
            s.Add(1);
            s.Add(6);
            s.Add(4);
            var view = s.GetViewBetween(3, 7);
            Assert.AreEqual(5, view.Count);
        }
        
    }

}
