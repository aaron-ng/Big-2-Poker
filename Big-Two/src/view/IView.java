package view;

import model.IBig2Model;

public interface IView<V> {

  V go(IBig2Model model);

}
