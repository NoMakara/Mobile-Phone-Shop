package com.project.mobile_phone_shop.Service;

import com.project.mobile_phone_shop.Entity.Color;

public interface ColorService {
    Color create(Color color);
    Color getColorById(Long id);
}
